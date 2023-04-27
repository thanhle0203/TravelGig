package com.thanhle.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.thanhle.domain.Booking;

import java.util.Date;
import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByCustomerMobile(String customerMobile);
    Booking findByBookedOnDate (Date bookedOnDate);
	Optional<Booking> findGuestsByBookingId(int bookingId);

    
}


