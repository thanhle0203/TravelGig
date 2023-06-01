package com.thanhle.repository;

import com.thanhle.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    // Additional custom queries can be defined here if needed
}
