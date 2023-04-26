package com.thanhle.controller;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/customer/{mobile}")
    public ResponseEntity<List<Booking>> getByCustomerMobile(@PathVariable("mobile") String mobile) {
        List<Booking> bookings = bookingService.findByCustomerMobile(mobile);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getByStatus(@PathVariable("status") String status) {
        List<Booking> bookings = bookingService.findByStatus(status);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}/guests")
    public ResponseEntity<List<Guest>> getGuestsByBookingId(@PathVariable("bookingId") int bookingId) {
        List<Guest> guests = bookingService.findGuestsByBookingId(bookingId);
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }
}
