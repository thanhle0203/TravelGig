package com.thanhle.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.InsuranceComponent;
import com.thanhle.component.InsurerComponent;

@RestController
public class GatewayController {
	
	@Autowired
	InsuranceComponent insuranceComponent;
	
	@RequestMapping(value = "/savedPlan", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> savePlan(@RequestBody JsonNode json) {
    	JsonNode plan = insuranceComponent.savePlan(json);
    	return new ResponseEntity<>(plan, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/autoPlan/{planAutoId}", method = RequestMethod.GET)
	public JsonNode searchPlan(@PathVariable String planAutoId, Principal principal){
		//String username = principal.getName();
		return insuranceComponent.findPlanId(planAutoId);
	}


}
