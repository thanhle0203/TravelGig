package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Insured;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, Long> {
	
}
