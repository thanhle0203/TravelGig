package com.thanhle.controller;

import org.springframework.http.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanhle.component.BookingComponent;
import com.thanhle.component.HotelComponent;
import com.thanhle.domain.Booking;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.repository.BookingRepository;
import com.thanhle.service.BookingService;
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
	BookingService bookingService;
    
    @Autowired 
	BookingRepository bookingRepository;
    
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
    
    @RequestMapping(value = "/saveBooking", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveBooking(@RequestBody JsonNode json) {
    	JsonNode booking = bookingComponent.saveBooking(json);
    	return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveGuest", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveGuest(@RequestBody JsonNode json) {
    	JsonNode guest =  bookingComponent.saveGuest(json);
    	return new ResponseEntity<>(guest, HttpStatus.OK);
    }
    
    /*
    @RequestMapping(value = "/getBookingsByPhones/{mobile}", method = RequestMethod.GET)
	public JsonNode findBookingsByPhones(@PathVariable String mobile, Principal principal){
		String username = principal.getName();
		User user = userService.findByUserName(username);
		mobile = user.getMobile();
		return bookingComponent.findBookingsByPhone(mobile);
	}
	*/
    
    @RequestMapping(value = "/getUpComingBookingsByPhone/{mobile}", method = RequestMethod.GET)
	public JsonNode findUpComingBookingsByPhone(@PathVariable String mobile, Principal principal){
		String username = principal.getName();
		User user = userService.findByUserName(username);
		mobile = user.getMobile();
		return bookingComponent.findUpcomingBookingsByPhone(mobile);
	}
    
    @RequestMapping(value = "/getCompletedBookingsByPhone/{mobile}", method = RequestMethod.GET)
   	public JsonNode findCompletedBookingsByPhone(@PathVariable String mobile, Principal principal){
   		String username = principal.getName();
   		User user = userService.findByUserName(username);
   		mobile = user.getMobile();
   		return bookingComponent.findCompletedBookingsByPhone(mobile);
   	}
    
    @RequestMapping(value = "/getBookingsByPhone/{mobile}", method = RequestMethod.GET)
   	public JsonNode findBookingsByPhone(@PathVariable String mobile, Principal principal){
   		String username = principal.getName();
   		User user = userService.findByUserName(username);
   		mobile = user.getMobile();
   		return bookingComponent.findBookingsByPhone(mobile);
   	}
    
    @GetMapping("/myBookings")
    public List<Booking> getBookings(Model model, Principal principal) {
		String username = principal.getName();
	    User currentUser = userService.findByUserName(username);
	    String mobile = currentUser.getMobile();
	    model.addAttribute("mobile", mobile);
	    
       List<Booking> bookings = bookingService.findByCustomerMobile(mobile);
     
        return bookings;
    }
  

}
