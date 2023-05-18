package com.thanhle.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thanhle.domain.Address;


@Repository
@CrossOrigin(origins = "http://localhost:8282")
public interface AddressRepository extends JpaRepository<Address, Long> {

}
