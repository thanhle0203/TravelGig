package com.thanhle.component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhle.domain.Booking;
import com.thanhle.dto.EmailDetails;
import com.thanhle.service.EmailService;

@Component
public class BookingComponent {
	
	@Autowired
	EmailService emailService;
	
	public JsonNode saveBooking(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/bookings", request, Object.class);

		Object object = (JsonNode) responseEntity.getBody();

		return (JsonNode) object;
		
	}
	
	public JsonNode saveGuest(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/guests", request, Object.class);

		JsonNode object = (JsonNode) responseEntity.getBody();

		return object;
		
	}
	
	/*
	public List<Booking> saveGuestBookings(JsonNode json, String phoneNumber) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/bookings/" + phoneNumber, Object.class);

		JsonNode objects = (JsonNode) responseEntity.getBody();
		
		ObjectMapper objMap = new ObjectMapper();
		JsonNode returnObj = objMap.convertValue(objects, JsonNode.class);

		return returnObj;
		
	}
	*/
	
	public List<Booking> saveGuestBookings(JsonNode json, String phoneNumber) {
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Booking[]> responseEntity = restTemplate.getForEntity("http://localhost:8484/bookings/" + phoneNumber, Booking[].class);
	    Booking[] bookingsArray = responseEntity.getBody();
	    List<Booking> bookings = Arrays.asList(bookingsArray);
	    return bookings;
	}

	
	public JsonNode sendBooking(JsonNode json) {

	    // get booking details from BookingMicroservice
	    String bookingId = json.get("bookingId").asText();
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(
	        "http://localhost:8484/booking?id=" + bookingId, JsonNode.class);
	    JsonNode bookingDetails = responseEntity.getBody();

	    // prepare email message with booking details
	    String msgBody = "Dear " + json.get("guestName").asText() + ",\n\n"
	            + "Your booking details are as follows:\n\n"
	            + "Booking ID: " + bookingDetails.get("bookingId").asText() + "\n"
	            + "Check-in Date: " + bookingDetails.get("checkInDate").asText() + "\n"
	            + "Check-out Date: " + bookingDetails.get("checkOutDate").asText() + "\n"
	            + "No. of Rooms: " + bookingDetails.get("noRooms").asText() + "\n"
	            + "Total Price: " + bookingDetails.get("price").asText() + "\n\n"
	            + "Thank you for choosing our hotel. We look forward to serving you!\n\n"
	            + "Best regards,\n"
	            + "The Hotel Team";

	    // send email using emailService
	    EmailDetails emailDetails = new EmailDetails();
	    emailDetails.setRecipient(json.get("email").asText());
	    emailDetails.setSubject("Booking Confirmation");
	    emailDetails.setMsgBody(msgBody);
	    
	    emailService.sendSimpleMail(emailDetails);

	    return bookingDetails;
	}
	

	public JsonNode getBooking(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		//ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8484/bookings/{customerMobile}", request, JsonNode.class);
		ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8484/bookings", request, JsonNode.class);

		JsonNode object = responseEntity.getBody();

		return object;
		
	}
	
	/*
	public JsonNode getBookingByMobile(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8484/bookings/{customerMobile}", request, JsonNode.class);
		//ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8484/bookings", request, JsonNode.class);

		JsonNode object = responseEntity.getBody();

		return object;
		
	}
	*/
	



}
