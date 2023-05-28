package com.thanhle.service;

import java.math.BigDecimal;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class StripeService {
	public Charge createCharge(BigDecimal amount, String currency, String token) throws StripeException {
		ChargeCreateParams chargeParams = ChargeCreateParams.builder()
			.setAmount((long) amount.multiply(BigDecimal.valueOf(100)).intValue())
			.setCurrency(currency)
			.setSource(token)
			.build();
		
		return Charge.create(chargeParams);
	}

}
