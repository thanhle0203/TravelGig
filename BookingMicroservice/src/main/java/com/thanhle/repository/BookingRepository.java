package com.thanhle.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thanhle.domain.Booking;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@CrossOrigin(origins = "http://localhost:8282")
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByCustomerMobile(String customerMobile);
	List<Booking> findByStatus(String string);
	List<Booking> findAll();
    List<Booking> findByBookingId(int bookingId);
}


