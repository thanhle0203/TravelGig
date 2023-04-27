package com.thanhle.component;

//import java.util.List;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelComponent {

		
	public JsonNode findHotelBySearchText(String searchText) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:8383/searchHotel/" + searchText, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public Object saveBooking(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/booking", request, Object.class);

		Object object = responseEntity.getBody();

		return object;
		
	}
	
}
