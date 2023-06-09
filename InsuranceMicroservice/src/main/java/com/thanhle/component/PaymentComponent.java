package com.thanhle.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PaymentComponent {
	public JsonNode savePayment(JsonNode json) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);		

		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8383/process-payment", request, Object.class);

		Object object = (JsonNode) responseEntity.getBody();

		return (JsonNode) object;
		
	}
	
	public JsonNode getPayment() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/process-payment", Object.class);

        Object objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
        return returnObj;
    }
	
	public JsonNode findPaymentByPhone(String phone) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/payment/phone/" + phone, Object.class);

        Object objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
        return returnObj;
    }

}
