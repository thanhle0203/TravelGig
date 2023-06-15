package com.thanhle.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.domain.Claim;
import com.thanhle.domain.Insured;
import com.thanhle.domain.PaymentData;
import com.thanhle.repository.ClaimRepository;
import com.thanhle.repository.InsuredRepository;
import com.thanhle.repository.PaymentDataRepository;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.repository.UserRepository;
import com.thanhle.service.EmailService;
import com.thanhle.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("user")
public class MailController {
	
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    InsuredRepository insuredRepository;
    
    @Autowired
    ClaimRepository claimRepository;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    PaymentDataRepository paymentDataRepository;

    @PostMapping(value = "/sendInsuredDetails/{insuredId}")
    public String sendMail(@PathVariable Long insuredId, Model model, Principal principal) {
        String result;
        String recipient;
        String username;

        // Get the logged in user
        username = principal.getName();
        User user = userRepository.findByUserName(username);
        model.addAttribute("username", username);
        recipient = user.getEmail();

        // Get the booking by ID
        Optional<Insured> optionalInsured = insuredRepository.findById(insuredId);

        if (optionalInsured.isPresent()) {
            Insured insured = optionalInsured.get();

            // Create email details object with booking details
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(recipient);
            emailDetails.setSubject("Insured Details for #" + insured.getId());
            emailDetails.setMsgBody("Dear " + username + ",\n\n"
                    + "Here are your auto plan details:\n\n"
                    + "Insured ID: #" + insured.getId() + "\n"
                    + "Insured Name: " + insured.getName() + "\n"
                    + "Insured Email: " + insured.getEmail() + "\n"
                    + "Insured Phone: " + insured.getPhone() + "\n"
                    + "Auto Plan Name: " + insured.getAutoInsurance().getAutoPlan().getName() + "\n"
                    + "Auto Plan Type: " + insured.getAutoInsurance().getAutoPlan().getType() + "\n"
                    + "Auto Plan Description: " + insured.getAutoInsurance().getAutoPlan().getDescription() + "\n"
                    + "Collision Deductible: $" + insured.getAutoInsurance().getCollisionDeductible() + "\n"
                    + "Collision Uninsured Motorist Deductible: $" + insured.getAutoInsurance().getUninsuredMotoristDeductible() + "\n"
                    + "Total Price: $" + insured.getAutoInsurance().getTotalPrice() + "\\year" + "\n\n"
                    + "Thank you for choosing AFI!");

            // Send the email
            result = emailService.sendSimpleMail(emailDetails);
        } else {
            result = "Booking not found";
        }

        return result;
    }
    
    @PostMapping(value = "/sendClaimDetails/{claimId}")
    public String sendClaim(@PathVariable Long claimId, Model model, Principal principal) {
        String result;
        String recipient;
        String username;

        // Get the logged in user
        username = principal.getName();
        User user = userRepository.findByUserName(username);
        model.addAttribute("username", username);
        recipient = user.getEmail();

        // Get the booking by ID
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);

        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();

            // Create email details object with booking details
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(recipient);
            emailDetails.setSubject("Claim Details for #" + claim.getId());
            emailDetails.setMsgBody("Dear " + username + ",\n\n"
                    + "Here are your claim details:\n\n"
                    + "Claim ID: #" + claim.getId() + "\n"
                    + "Accident Date: " + claim.getAccidentDate() + "\n"
                    + "Vehicle Make: " + claim.getVehicle().getMake() + "\n"
                    + "Vehicle Model: " + claim.getVehicle().getModel() + "\n"
                    + "Vehicle Make: " + claim.getVehicle().getYear() + "\n"
                    + "Description: " + claim.getDescription() + "\n"
                    + "Repair Price: " + claim.getRepairPrice() + "\n"
                    + "Claim Status: " + claim.getStatus() + "\n"
                    + "Claim Phone Number: " + claim.getPhone() + "\n"
                    + "Thank you for choosing AFI!");

            // Send the email
            result = emailService.sendSimpleMail(emailDetails);
        } else {
            result = "Booking not found";
        }

        return result;
    }

    
    @PostMapping(value = "/sendPaymentDetails/{paymentId}")
    public String sendPayment(@PathVariable Long paymentId, Model model, Principal principal) {
        String result;
        String recipient;
        String username;

        // Get the logged in user
        username = principal.getName();
        User user = userRepository.findByUserName(username);
        model.addAttribute("username", username);
        recipient = user.getEmail();

        // Get the booking by ID
        Optional<PaymentData> optionalPaymentData = paymentDataRepository.findById(paymentId);

        if (optionalPaymentData .isPresent()) {
            PaymentData payment = optionalPaymentData.get();

            // Create email details object with booking details
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(recipient);
            emailDetails.setSubject("Payment Details for #" + payment.getId());
            emailDetails.setMsgBody("Dear " + username + ",\n\n"
                    + "Here are your payment details:\n\n"
                    + "Payment ID #: " + payment.getId() + "\n"
                    + "Payment Name: " + payment.getNameOnCard() + "\n"               
                    + "Card Number: " + payment.getCardNumber() + "\n"
                    + "Payment Amount: $" + payment.getTotalAmount() + "\n"
                    + "Receipt URL: " + payment.getReceiptUrl() + "\n"
                    + "Thank you for choosing AFI!");

            // Send the email
            result = emailService.sendSimpleMail(emailDetails);
        } else {
            result = "Payment not found";
        }

        return result;
    }

}
