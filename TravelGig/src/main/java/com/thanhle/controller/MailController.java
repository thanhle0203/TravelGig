package com.thanhle.controller;

import com.thanhle.domain.Booking;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.repository.BookingRepository;
import com.thanhle.repository.UserRepository;
import com.thanhle.service.EmailService;
import com.thanhle.service.UserService;

import java.security.Principal;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class MailController {
	
	@Autowired 
	private User user;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private EmailService emailService;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private Booking booking;
    
    @Autowired
    private BookingRepository bookingRepository;
    

    @ModelAttribute("booking")
    public Booking getBooking() {
        return new Booking();
    }
    
    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @PostMapping(value = "/sendBookingDetails/{bookingId}")
    public String sendMail(@PathVariable int bookingId) {
        String result;
        
        String recipient;
        String username;

     // Get the logged in user
     	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     	username = auth.getName(); //get logged in username
     	User user = userService.findByUserName(username);
     	recipient = user.getEmail();
     		
        Booking booking = bookingRepository.findByBookingId(bookingId).orElse(null);

        // Create email details object with booking details
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(recipient);
        emailDetails.setSubject("Booking Details for #" + booking.getBookingId());
        emailDetails.setMsgBody("Dear " + username + ",\n\n"
                + "Here are your booking details:\n\n"
                + "Booking ID: #" + booking.getBookingId() + "\n"
                + " bookingHotelId: " + booking.getHotelId() + "\n"
                + "Check-in Date: " + booking.getCheckInDate() + "\n"
                + "Check-out Date: " + booking.getCheckOutDate() + "\n"
                + "Room Type: " + booking.getRoomType() + "\n"
                + "Number of Guests: " + booking.getGuests() + "\n"
                + "Total Price: " + booking.getPrice() + "\n\n"
                + "Thank you for choosing our hotel!");

        // Send the email
        result = emailService.sendSimpleMail(emailDetails);

        return result;
    }


   
}
