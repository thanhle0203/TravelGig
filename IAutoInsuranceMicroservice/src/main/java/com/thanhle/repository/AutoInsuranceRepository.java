package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.AutoInsurance;

@Repository
public interface AutoInsuranceRepository extends JpaRepository<AutoInsurance, Long> {
    // Add any additional custom queries or methods for AutoInsurance entity
}
