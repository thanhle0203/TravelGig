package com.thanhle.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhle.domain.AutoPlan;

public interface AutoPlanRepository extends JpaRepository<AutoPlan, Long> {
	AutoPlan findByName(String name);
}
