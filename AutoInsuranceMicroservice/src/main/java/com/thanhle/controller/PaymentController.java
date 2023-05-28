package com.thanhle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.thanhle.domain.PaymentData;

@RestController
public class PaymentController {
    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentData paymentData) throws StripeException {
        return ResponseEntity.ok("Payment processed successfully");
    }
}
