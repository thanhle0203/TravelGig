package com.thanhle.service;

import com.thanhle.domain.PaymentData;

public interface PaymentDataService {
	PaymentData savePaymentData(PaymentData paymentData);
	PaymentData getPaymentDataByPhone(String phone);
}
