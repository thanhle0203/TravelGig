package com.thanhle.service;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImp implements BookingService {

	@Autowired
    private BookingRepository bookingRepository;


    @Override
    public List<Booking> findByCustomerMobile(String mobile) {
        return bookingRepository.findByCustomerMobile(mobile);
    }

    @Override
    public List<Booking> findByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public List<Guest> findGuestsByBookingId(int bookingId) {
        return bookingRepository.findGuestsByBookingId(bookingId);
    }

	
}
