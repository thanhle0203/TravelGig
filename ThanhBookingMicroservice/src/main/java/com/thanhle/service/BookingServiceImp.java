package com.thanhle.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.repository.BookingRepository;

@Service
@CrossOrigin(origins = "http://localhost:8282")
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findByCustomerMobile(String customerMobile) {
        return bookingRepository.findByCustomerMobile(customerMobile);
    }

    @Override
    public List<Booking> findByBookingId(int bId) {
        return bookingRepository.findByBookingId(bId);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getUpcomingdBookings() {
        return bookingRepository.findByStatus("coming");
    }

    @Override
    public List<Booking> getCompleteddBookings() {
        return bookingRepository.findByStatus("completed");
    }
    
    @Override
    public Booking saveCancelledBooking(Booking booking) {
        booking.setStatus("cancelled");
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getCancelledBookings() {
        return bookingRepository.findByStatus("cancelled");
    }

}
