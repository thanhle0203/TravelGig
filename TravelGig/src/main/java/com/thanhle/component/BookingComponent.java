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
	
	
	public JsonNode getBookingId(String bookingId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8484/bookings/" + bookingId, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
		
	}
	/*
	public JsonNode findBookingsByPhones(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8484/bookings/mobiles/" + mobile, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	*/
	public JsonNode findBookingsByPhone(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8484/bookings/mobile/" + mobile, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode findUpcomingBookingsByPhone(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8484/bookings/upcomingMobile/" + mobile, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode findCompletedBookingsByPhone(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8484/bookings/completedMobile/" + mobile, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

}
