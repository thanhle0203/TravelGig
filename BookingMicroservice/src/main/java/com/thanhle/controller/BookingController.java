package com.thanhle.controller;

import com.thanhle.domain.Booking;

import com.thanhle.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class BookingController {

    @Autowired
    BookingService bookingService;

	@PostMapping(value = "/booking")
	@ResponseBody
	public Booking saveGuest(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping(value = "/booking")
	@ResponseBody
	public List<Booking> getAllBookings() {
	    return bookingService.getAll();
	}
	
	@DeleteMapping(value = "/booking/{id}")
	@ResponseBody
	public void deleteBooking(@PathVariable int id) {
		bookingService.deleteBooking(id);
	}

	@PutMapping(value = "/booking/{id}")
	@ResponseBody
	public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking) {
		booking.setBookingId(id);
		return bookingService.saveBooking(booking);
	}
}
