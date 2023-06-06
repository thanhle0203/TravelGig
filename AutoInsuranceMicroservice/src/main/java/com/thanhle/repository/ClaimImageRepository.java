package com.thanhle.repository;

import com.thanhle.domain.Claim;
import com.thanhle.domain.ClaimImage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:8282")
public interface ClaimImageRepository extends JpaRepository<ClaimImage, Long> {
	
	
}