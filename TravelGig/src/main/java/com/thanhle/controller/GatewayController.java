package com.thanhle.controller;

import org.springframework.http.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanhle.component.BookingComponent;
import com.thanhle.component.HotelComponent;
import com.thanhle.domain.Booking;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.service.EmailService;
import com.thanhle.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SessionAttributes("user")
public class GatewayController {

    @Autowired
    HotelComponent hotelComponent;
    
    @Autowired
    BookingComponent bookingComponent;
    
    @Autowired
	BookingController bookingController;
    
    @Autowired
    UserService userService;
    
   @Autowired
   EmailService emailService;
    
    @RequestMapping(value = "/searchHotelByName/{searchString}", method = RequestMethod.GET)
	public JsonNode searchHotel(@PathVariable String searchString, Principal principal){
		String username = principal.getName();
		System.out.println("Welcome -----------------" + username);
		return hotelComponent.findHotelBySearchText(searchString);
	}
    
    @RequestMapping(value = "/saveGuest", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveGuest(@RequestBody JsonNode json) {
    	JsonNode guest =  bookingComponent.saveGuest(json);
    	return new ResponseEntity<>(guest, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveBooking", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveBooking(@RequestBody JsonNode json) {
    	JsonNode booking = bookingComponent.saveBooking(json);
    	return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getBooking", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> getBooking(@RequestBody JsonNode json) {
    	JsonNode booking = bookingComponent.getBooking(json);
    	return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    
    /*
    
    @GetMapping(value = "/getBookings/{customerMobile}")
    public ResponseEntity<JsonNode> getBookingByMobile(@RequestBody JsonNode json) {
        JsonNode booking = bookingComponent.getBookingByMobile(json);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    */

    
   // @RequestMapping(value = "/getBookings", method = RequestMethod.GET)
	//public String myBookings(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		 // get the mobile number of the logged-in user from the database
    	/*
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    User user = userService.findByUserName(username);
	    String mobileNumber = user.getMobile();
	    */
    	
	    // add the mobile number to the model
	   // model.addAttribute("mobileNumber", mobileNumber);
	    //String mobileNo = (String) session.getAttribute("mobileNo");
	   // List<Booking> bookings = bookingService.getBookingsByMobileNo(mobileNumber);
	   // List<Booking> bookings = bookingService.getBookingsByMobileNo(mobileNumber);
	    /*
	    List<Booking> bookings = bookingController.getAllBookings();
	    List<Booking> upcomingBookings = new ArrayList<>();
	    List<Booking> completedBookings = new ArrayList<>();
	    List<Booking> cancelledBookings = new ArrayList<>();
	    LocalDate currentDate = LocalDate.now();

	    for (Booking booking : bookings) {
	        LocalDate checkInDate = LocalDate.parse((CharSequence) booking.getCheckInDate());
	        String status = booking.getStatus();

	        if (status.equalsIgnoreCase("upcoming") && checkInDate.isAfter(currentDate)) {
	            upcomingBookings.add(booking);
	        } else if (status.equalsIgnoreCase("upcoming") && checkInDate.isBefore(currentDate)) {
	            completedBookings.add(booking);
	        } else if (status.equalsIgnoreCase("cancelled")) {
	            cancelledBookings.add(booking);
	        }
	    }

	    model.addAttribute("upcomingBookings", upcomingBookings);
	    model.addAttribute("completedBookings", completedBookings);
	    model.addAttribute("cancelledBookings", cancelledBookings);
		*/
	   // return "MyBookings";
	//}
    
  
    
   

}
