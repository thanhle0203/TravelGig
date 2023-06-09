package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.PaymentData;
import com.thanhle.repository.PaymentDataRepository;

@Service
public class PaymentDataServiceImpl implements PaymentDataService {

	private final PaymentDataRepository paymentDataRepository;
	
	@Autowired
	public PaymentDataServiceImpl(PaymentDataRepository paymentDataRepository) {
		this.paymentDataRepository = paymentDataRepository;
	} 
	
	@Override
	public PaymentData savePaymentData(PaymentData paymentData) {
		return paymentDataRepository.save(paymentData);
	}

	@Override
	public PaymentData getPaymentDataByPhone(String phone) {
		// TODO Auto-generated method stub
		return paymentDataRepository.findByPhone(phone);
	}

}
