package com.thanhle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>{

	public Building findByBuName(String buName);
	
}