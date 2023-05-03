package com.thanhle.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.thanhle.domain.Booking;
import com.thanhle.domain.User;
import com.thanhle.repository.BookingRepository;
import com.thanhle.service.BookingService;
import com.thanhle.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class WelcomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired 
	BookingRepository bookingRepository;
	
	@Autowired
	BookingController bookingController;
	
	@RequestMapping(value = "/welcome")
	public String welcome(Principal principal, Model model) {
		if (principal != null) {
			System.out.print("Welcome to Controller: " + principal.getName());
			model.addAttribute("username", principal.getName());
		}
		else {
			model.addAttribute("user", null);
		}
		return "hotel";
	}
	

	//@GetMapping(value = "/getBookings")
	//public String myBookings(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		
	    //return "getBookings";
	//}

	@GetMapping("/getBookings")
	public String showMyBookings(Model model, Principal principal) {
	    String username = principal.getName();
	    User currentUser = userService.findByUserName(username);
	    String mobile = currentUser.getMobile();
	    model.addAttribute("mobile", mobile);
	    return "getBookings";
	}

	
	@GetMapping("/mybookings")
    public List<Booking> getBookings(Principal principal) {
        // get the currently logged in user
       //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = principal.getName();
       //String username = authentication.getName();
       User currentUser = userService.findByUserName(username);		
       String userMobile = currentUser.getMobile();
        // fetch all bookings for the logged in user
       //List<Booking> bookings = bookingRepository.findByCustomerMobile(mobileNumber);
       List<Booking> bookings = bookingService.findByCustomerMobile(userMobile);
     

        return bookings;
    }


    @PostMapping("/cancelBooking/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        // get the booking by ID
        Optional<Booking> optionalBooking = bookingRepository.findByBookingId(bookingId);

        if (!optionalBooking.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = optionalBooking.get();

        // set the booking status to cancelled
        booking.setStatus("cancelled");

        // save the updated booking
        bookingRepository.save(booking);

        return ResponseEntity.ok("Booking cancelled successfully.");
    }

}
