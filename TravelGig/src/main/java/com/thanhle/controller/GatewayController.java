package com.thanhle.controller;

import org.springframework.http.*;
import java.security.Principal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanhle.component.BookingComponent;
import com.thanhle.component.HotelComponent;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.service.EmailService;
import com.thanhle.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public String sendMail(@RequestBody EmailDetails emailDetails) {
    	return emailService.sendSimpleMail(emailDetails);
    }
    
   

}
