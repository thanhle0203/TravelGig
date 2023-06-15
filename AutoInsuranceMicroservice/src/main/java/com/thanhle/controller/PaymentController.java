package com.thanhle.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.ChargeListParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.PaymentMethodCreateParams.*;
import com.thanhle.config.StripeConfig;
import com.thanhle.domain.Address;
import com.thanhle.domain.PaymentData;
import com.thanhle.repository.PaymentDataRepository;
import com.thanhle.service.PaymentDataService;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
public class PaymentController {
    @Autowired
    StripeConfig stripeConfig;
    
    @Autowired
    PaymentDataService paymentDataService;
    
    @Autowired
    PaymentDataRepository paymentDataRepository;

    @PostMapping("/process-payment")
    public ResponseEntity<PaymentData> processPayment(@RequestBody PaymentData paymentData) throws StripeException {
        // Log the received payment data
        System.out.println("Received payment data:");
        System.out.println("Payment Method: " + paymentData.getPaymentMethod());
        System.out.println("Card Number: " + paymentData.getCardNumber());
        System.out.println("Expiry Date: " + paymentData.getExpiryDate());
        System.out.println("CVV: " + paymentData.getCvv());
        System.out.println("Name on Card: " + paymentData.getNameOnCard());
        System.out.println("Total Amount: " + paymentData.getTotalAmount());
        System.out.println("Receipt URL: " + paymentData.getReceiptUrl());

        // Log the billing address
        Address billingAddress = paymentData.getBillingAddress();
        System.out.println("Billing Address:");
        System.out.println("Street: " + billingAddress.getStreet());
        System.out.println("City: " + billingAddress.getCity());
        System.out.println("State: " + billingAddress.getState());
        System.out.println("Zip Code: " + billingAddress.getZipCode());
        System.out.println("Email: " + billingAddress.getEmail());
        System.out.println("Phone: " + paymentData.getPhone());

        // Set the Stripe API key from the configuration
        String apiKey = stripeConfig.getStripeApiKey();
        Stripe.apiKey = apiKey;

        // Create a new customer
        CustomerCreateParams customerParams = CustomerCreateParams.builder()
                .setEmail(paymentData.getBillingAddress().getEmail())
                .setName(paymentData.getNameOnCard())
                .setPhone(paymentData.getPhone())             
                .build();
        Customer customer = Customer.create(customerParams);

        // Parse the expiry date string into a YearMonth object
        YearMonth expiryDate = YearMonth.parse(paymentData.getExpiryDate(), DateTimeFormatter.ofPattern("MM/yyyy"));

        // Create the payment method using the card information
        PaymentMethodCreateParams.CardDetails cardDetails = PaymentMethodCreateParams.CardDetails.builder()
                .setNumber(paymentData.getCardNumber())
                .setExpMonth((long) expiryDate.getMonthValue())
                .setExpYear((long) expiryDate.getYear())
                .setCvc(paymentData.getCvv())              
                .build();

        PaymentMethodCreateParams.BillingDetails billingDetails = PaymentMethodCreateParams.BillingDetails.builder()
                .setName(paymentData.getNameOnCard())
                .setEmail(billingAddress.getEmail())
                .setPhone(paymentData.getPhone())
                .setAddress(BillingDetails.Address.builder()
                        .setLine1(billingAddress.getStreet())
                        .setCity(billingAddress.getCity())
                        .setState(billingAddress.getState())
                        .setPostalCode(billingAddress.getZipCode())
                        .setCountry("US")
                        .build())
                .build();

        PaymentMethodCreateParams paymentMethodParams = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(cardDetails)
                .setBillingDetails(billingDetails)
                .build();

        PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

        // Perform any necessary payment processing or integration with a payment gateway
        PaymentIntentCreateParams.Builder paramsBuilder = new PaymentIntentCreateParams.Builder()
                .setAmount((long) (paymentData.getTotalAmount() * 100))
                .addPaymentMethodType(paymentData.getPaymentMethod())
                .setPaymentMethod(paymentMethod.getId())
                .setCurrency("usd")
                .setDescription("Payment for auto insurance")
                .setCustomer(customer.getId())
                .setReceiptEmail(paymentData.getBillingAddress().getEmail())             
                .setReturnUrl(paymentData.getReceiptUrl())
                .setConfirm(true) // Confirm the payment intent

                // Add address checks
                .setUseStripeSdk(true)
                .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                .addPaymentMethodType("card_present");

        PaymentIntentCreateParams params = paramsBuilder.build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);

     // Retrieve the charges associated with the payment intent
        ChargeListParams chargeListParams = ChargeListParams.builder()
                .setPaymentIntent(paymentIntent.getId())
                .build();
        List<Charge> charges = Charge.list(chargeListParams).getData();

        String paymentIntentId = paymentIntent.getId();
        Charge associatedCharge = null;

        for (Charge charge : charges) {
            if (charge.getPaymentIntent() != null && charge.getPaymentIntent().equals(paymentIntentId)) {
                associatedCharge = charge;
                break;
            }
        }
        
        // Update the payment status based on the payment processing result
        boolean paymentSuccessful = paymentIntent.getStatus().equals("succeeded");

        PaymentData savedPaymentData = paymentDataService.savePaymentData(paymentData);
        
        if (paymentSuccessful) {
        	// Check if there is an associated charge and retrieve the receipt URL
            if (associatedCharge != null) {
                savedPaymentData.setReceiptUrl(associatedCharge.getReceiptUrl());
            } else {
                // Handle the case where there is no associated charge or receipt URL
                savedPaymentData.setReceiptUrl(null);
                System.out.println("No receipt URL available for the payment.");
            }
            
            // Save the payment data with the updated receipt URL
            savedPaymentData = paymentDataService.savePaymentData(savedPaymentData);
            
        	// Send the receipt email to the customer
            PaymentIntentUpdateParams.Builder updateParamsBuilder = PaymentIntentUpdateParams.builder()
                    .setReceiptEmail(savedPaymentData.getBillingAddress().getEmail())
               
                    //.setReceiptEmail(savedPaymentData.getReceiptUrl())
                    ;
            
            PaymentIntentUpdateParams updateParams = updateParamsBuilder.build();
            paymentIntent.update(updateParams);

            // Return the payment receipt URL in the response
            //savedPaymentData.setReceiptUrl(associatedCharge.getReceiptUrl());
            

         
            System.out.println("Receipt URL: " + savedPaymentData.getReceiptUrl());
            
            //return ResponseEntity.ok("Payment processed successfully");
            return new ResponseEntity<>(savedPaymentData, HttpStatus.CREATED);
        
        } else {
            // Perform any necessary actions for a failed payment
            //return new ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payment/{paymentIntentId}")
    public ResponseEntity<String> getPaymentDetails(@PathVariable String paymentIntentId) throws StripeException {
        // Set the Stripe API key from the configuration
        String apiKey = stripeConfig.getStripeApiKey();
        Stripe.apiKey = apiKey;

        try {
            // Retrieve the payment intent from the Stripe API
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Log the payment details
            System.out.println("Payment Intent ID: " + paymentIntent.getId());
            System.out.println("Amount: " + paymentIntent.getAmount());
            System.out.println("Currency: " + paymentIntent.getCurrency());
            System.out.println("Status: " + paymentIntent.getStatus());
            // ... Log other relevant payment details as needed

            return ResponseEntity.ok("Payment details retrieved successfully");
        } catch (StripeException e) {
            // Handle any errors that occur during the retrieval process
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve payment details");
        }
    }
    
    @GetMapping("/payment/phone/{phone}")
    public ResponseEntity<PaymentData> getPaymentByPhone(@PathVariable String phone){
    	PaymentData paymentData = paymentDataService.getPaymentDataByPhone(phone);
    	
        if (paymentData != null) {
            return ResponseEntity.ok(paymentData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

    
    
}