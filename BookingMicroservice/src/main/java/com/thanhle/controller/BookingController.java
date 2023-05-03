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

	//@PostMapping(value = "/bookings")
	//@ResponseBody
	//public Booking saveBookings(@RequestBody Booking booking, @RequestParam Set<Guest> guests) {
		//Guest guest = guestService.getGuestById(guestId);
		//return bookingService.saveBookings(booking, guests);
	//}
	
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
	
	@GetMapping(value = "/bookings/{customerMobile}")
	@ResponseBody
	public List<Booking> getBookingsByMobile(@PathVariable String customerMobile) {
	    return bookingService.findByCustomerMobile(customerMobile);
	}
	
	@DeleteMapping(value = "/bookings/{id}")
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
	
	@PostMapping(value = "/bookings/upcoming")
	@ResponseBody
	public Booking saveUpcomingBooking(@RequestBody Booking booking) {
	    // save the booking to the upcoming bookings list
	    return bookingService.saveUpcomingBooking(booking);
	}

	@PostMapping(value = "/bookings/completed")
	@ResponseBody
	public Booking saveCompletedBooking(@RequestBody Booking booking) {
	    // save the booking to the completed bookings list
	    return bookingService.saveCompletedBooking(booking);
	}

	@PostMapping(value = "/bookings/cancelled")
	@ResponseBody
	public Booking saveCancelledBooking(@RequestBody Booking booking) {
	    // save the booking to the cancelled bookings list
	    return bookingService.saveCancelledBooking(booking);
	}

	@GetMapping(value = "/bookings/upcoming")
	@ResponseBody
	public List<Booking> getUpcomingBookings() {
	    // return a list of upcoming bookings
	    return bookingService.getUpcomingBookings();
	}

	@GetMapping(value = "/bookings/completed")
	@ResponseBody
	public List<Booking> getCompletedBookings() {
	    // return a list of completed bookings
	    return bookingService.getCompletedBookings();
	}

	@GetMapping(value = "/bookings/cancelled")
	@ResponseBody
	public List<Booking> getCancelledBookings() {
	    // return a list of cancelled bookings
	    return bookingService.getCancelledBookings();
	}
	
	

}
