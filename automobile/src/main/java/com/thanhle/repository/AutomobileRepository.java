package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Automobile;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Integer>{
	public Automobile findByName(String name);
}