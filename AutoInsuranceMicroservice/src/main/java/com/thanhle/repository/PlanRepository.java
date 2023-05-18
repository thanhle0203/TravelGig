package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findByName(String name);
    // Add any additional custom queries or methods for Plan entity
}
