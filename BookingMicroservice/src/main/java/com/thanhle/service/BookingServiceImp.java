package com.thanhle.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.repository.BookingRepository;

@Service
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
    public Booking saveUpcomingBooking(Booking booking) {
        Date checkInDate = booking.getCheckInDate();
        Date checkOutDate = booking.getCheckOutDate();
        Date currentDate = new Date();

        if (checkInDate.after(currentDate) || (checkInDate.before(currentDate) && checkOutDate.after(currentDate))) {
            booking.setStatus("upcoming");
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking saveCompletedBooking(Booking booking) {
        Date checkInDate = booking.getCheckInDate();
        Date checkOutDate = booking.getCheckOutDate();
        Date currentDate = new Date();

        if (checkOutDate.before(currentDate)) {
            booking.setStatus("completed");
        }
        return bookingRepository.save(booking);
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
