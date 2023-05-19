package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Add any additional custom queries or methods for Driver entity
}
