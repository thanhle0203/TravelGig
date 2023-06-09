package com.thanhle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

@Configuration
public class StripeConfig {
    @Value("${stripe.api.secretKey}")
    private String stripeApiKey;

    public StripeConfig() {
        Stripe.apiKey = stripeApiKey;
    }

    public String getStripeApiKey() {
        return stripeApiKey;
    }
}
