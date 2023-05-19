package com.thanhle.repository;

import com.thanhle.domain.AutoInsurance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoInsuranceRepository extends JpaRepository<AutoInsurance, Long> {

	List<AutoInsurance> findBySelectedTrue();

}
