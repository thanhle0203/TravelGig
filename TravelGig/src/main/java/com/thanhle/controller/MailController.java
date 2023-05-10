package com.thanhle.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.BookingComponent;
import com.thanhle.domain.Booking;
import com.thanhle.domain.User;
import com.thanhle.dto.EmailDetails;
import com.thanhle.repository.BookingRepository;
import com.thanhle.repository.UserRepository;
import com.thanhle.service.EmailService;
import com.thanhle.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("user")
public class MailController {
	@Autowired
	private BookingComponent bookingComponent;
	
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
    public String sendMail(@PathVariable int bookingId, Model model, Principal principal) {
        String result;
        String recipient;
        String username;

        // Get the logged in user
        username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("username", username);
        recipient = user.getEmail();

        // Get the booking by ID
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Create email details object with booking details
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(recipient);
            emailDetails.setSubject("Booking Details for #" + booking.getBookingId());
            emailDetails.setMsgBody("Dear " + username + ",\n\n"
                    + "Here are your booking details:\n\n"
                    + "Booking ID: #" + booking.getBookingId() + "\n"
                    + "Hotel ID: " + booking.getHotelId() + "\n"
                    + "Check-in Date: " + booking.getCheckInDate() + "\n"
                    + "Check-out Date: " + booking.getCheckOutDate() + "\n"
                    + "Room Type: " + booking.getRoomType() + "\n"
                    + "Number of Guests: " + booking.getGuests() + "\n"
                    + "Total Price: " + booking.getPrice() + "\n\n"
                    + "Thank you for choosing our hotel!");

            // Send the email
            result = emailService.sendSimpleMail(emailDetails);
        } else {
            result = "Booking not found";
        }

        return result;
    }

    
    @PostMapping(value = "/sendBooking/{bookingId}")
    public String sendMailBooking(@RequestBody JsonNode json, @PathVariable String bookingId, Model model, Principal principal) {
        JsonNode booking = bookingComponent.getBookingId(bookingId);
        String result;
        String recipient;

        // Get the email of the logged in user
        String username = principal.getName();
        User user = userService.findByUserName(username);
        model.addAttribute("username", username);
        String email = user.getEmail();
        if (booking != null && booking.get("email").asText().equals(email)) {
            recipient = email;
            EmailDetails emailDetails = new EmailDetails();
            // Create email details object with booking details
            emailDetails.setRecipient(recipient);
            emailDetails.setSubject("Booking Details for #" + booking.get("bookingId").asText());
            emailDetails.setMsgBody("Dear " + booking.get("guestName").asText() + ",\n\n"
                    + "Here are your booking details:\n\n"
                    + "Booking ID: #" + booking.get("bookingId").asText() + "\n"
                    + "Check-in Date: " + booking.get("checkInDate").asText() + "\n"
                    + "Check-out Date: " + booking.get("checkOutDate").asText() + "\n"
                    + "No. of Rooms: " + booking.get("noRooms").asText() + "\n"
                    + "Total Price: " + booking.get("price").asText() + "\n\n"
                    + "Thank you for choosing our hotel!");

            // Send the email
            result = emailService.sendSimpleMail(emailDetails);
        } else {
            result = "Error: Booking not found or unauthorized access";
        }

        return result;
    }


}
