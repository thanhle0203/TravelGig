package com.thanhle.controller;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.service.BookingService;
import com.thanhle.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class BookingController {

    @Autowired
    BookingService bookingService;
    
    @Autowired
    GuestService guestService;

	@PostMapping(value = "/bookings")
	@ResponseBody
	public Booking saveBookings(@RequestBody Booking booking, @RequestParam Set<Guest> guests) {
		//Guest guest = guestService.getGuestById(guestId);
		return bookingService.saveBookings(booking, guests);
	}
	
	@PostMapping(value = "/booking")
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
	
	@DeleteMapping(value = "/booking/{id}")
	@ResponseBody
	public void deleteBooking(@PathVariable int id) {
		bookingService.deleteBooking(id);
	}

	//@PutMapping(value = "/booking/{id}")
	//@ResponseBody
	//public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking) {
		//booking.setBookingId(id);
		//return bookingService.saveBooking(booking, guests);
	//}
}
