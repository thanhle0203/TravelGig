package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.PaymentData;

@Repository
public interface PaymentDataRepository extends JpaRepository<PaymentData, Long>{
	PaymentData findByPhone(String phone);
}
