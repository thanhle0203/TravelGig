package com.thanhle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.InsurerComponent;

@RestController
public class GatewayController {
	
	@Autowired
	InsurerComponent insurerComponent;
	
	@RequestMapping(value = "/saveInsurer", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveInsurer(@RequestBody JsonNode json) {
    	JsonNode insurer = insurerComponent.saveInsurer(json);
    	return new ResponseEntity<>(insurer, HttpStatus.OK);
    }
	


}
