package com.thanhle.controller;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.service.BookingService;
import com.thanhle.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class BookingController {

    @Autowired
    BookingService bookingService;
    
    @Autowired
    GuestService guestService;

	@PostMapping(value = "/bookings")
	@ResponseBody
	public Booking saveBooking(@RequestBody Booking booking) {
		//Guest guest = guestService.getGuestById(guestId);
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping(value = "/bookings")
	@ResponseBody
	public List<Booking> getAllBookings() {
	    return bookingService.getAll();
	}
	
	@GetMapping(value = "/bookings/{bookingId}")
	@ResponseBody
	public List<Booking> getBookingsById(@PathVariable int bookingId) {
	    return bookingService.findByBookingId(bookingId);
	}
	
	@GetMapping(value = "/bookings/mobile/{customerMobile}")
	@ResponseBody
	public List<Booking> getBookingsByMobile(@PathVariable String customerMobile) {
	    return bookingService.findByCustomerMobile(customerMobile);
	}
	/*
	@GetMapping(value = "/bookings/mobiles/{customerMobile}")
	@ResponseBody
	public List<Booking> getBookingsByMobiles(@PathVariable String customerMobile) {
	    List<Booking> bookings = bookingService.findByCustomerMobile(customerMobile);
	    List<Booking> upcomingBookings = new ArrayList<>();
	    List<Booking> completedBookings = new ArrayList<>();
	    Date currentDate = new Date();
	    for (Booking booking : bookings) {
	    	Date checkInDate = booking.getCheckInDate();
            Date checkOutDate = booking.getCheckOutDate();
	        if (booking.getStatus().equals("upcoming")) {        
	            if (checkInDate.after(currentDate) || (checkInDate.before(currentDate) && checkOutDate.after(currentDate))) {
	                upcomingBookings.add(booking);
	            }
	        } else if (booking.getStatus().equals("completed")) {
	            if (checkOutDate.before(currentDate)) {
	                completedBookings.add(booking);
	            }
	        }
	    }
	    if (upcomingBookings.isEmpty() && completedBookings.isEmpty()) {
	        return null;
	    }
	    return upcomingBookings.isEmpty() ? completedBookings : upcomingBookings;
	}

	*/

	@PostMapping(value = "/bookings/cancelled")
	@ResponseBody
	public Booking saveCancelledBooking(@RequestBody Booking booking) {
	    // save the booking to the cancelled bookings list
	    return bookingService.saveCancelledBooking(booking);
	}

	@GetMapping(value = "/bookings/cancelled")
	@ResponseBody
	public List<Booking> saveCancelledBookings() {
	    // save the booking to the cancelled bookings list
	    return bookingService.getCancelledBookings();
	}
	
	@GetMapping(value = "/bookings/upcomingMobile/{customerMobile}")
	@ResponseBody
	public List<Booking> getUpcomingBookingsByMobile(@PathVariable String customerMobile) {
	    List<Booking> bookings = bookingService.findByCustomerMobile(customerMobile);
	    List<Booking> upcomingBookings = new ArrayList<>();
	    Date currentDate = new Date();
	    for (Booking booking : bookings) {
	        Date checkInDate = booking.getCheckInDate();
	        Date checkOutDate = booking.getCheckOutDate();
	        if (booking.getStatus().equals("upcoming")) {        
	            if (checkInDate.after(currentDate) || (checkInDate.before(currentDate) && checkOutDate.after(currentDate))) {
	                upcomingBookings.add(booking);
	            }
	        } 
	    }
	    if (upcomingBookings.isEmpty()) {
	        return null;
	    }
	    return upcomingBookings;
	}

	@GetMapping(value = "/bookings/completedMobile/{customerMobile}")
	@ResponseBody
	public List<Booking> getCompletedBookingsByMobile(@PathVariable String customerMobile) {
	    List<Booking> bookings = bookingService.findByCustomerMobile(customerMobile);
	    List<Booking> completedBookings = new ArrayList<>();
	    Date currentDate = new Date();
	    for (Booking booking : bookings) {
	        Date checkOutDate = booking.getCheckOutDate();
	        if (booking.getStatus().equals("completed")) {        
	            if (checkOutDate.before(currentDate)) {
	                completedBookings.add(booking);
	            }
	        } 
	    }
	    if (completedBookings.isEmpty()) {
	        return null;
	    }
	    return completedBookings;
	}

	
}