package com.thanhle.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class BookingComponent {
	public JsonNode saveBooking(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/booking", request, Object.class);

		Object object = (JsonNode) responseEntity.getBody();

		return (JsonNode) object;
		
	}
	
	public JsonNode saveGuest(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/guest", request, Object.class);

		JsonNode object = (JsonNode) responseEntity.getBody();

		return object;
		
	}
}
