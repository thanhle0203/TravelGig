package com.thanhle.repository;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByCustomerMobile(String mobile);
    
    List<Booking> findByStatus(String status);

	List<Guest> findGuestsByBookingId(int bookingId);

    
}


