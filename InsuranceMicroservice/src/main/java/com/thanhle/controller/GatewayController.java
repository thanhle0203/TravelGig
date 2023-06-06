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
import com.thanhle.component.ClaimComponent;
import com.thanhle.component.InsuranceComponent;
import com.thanhle.component.InsuredComponent;
import com.thanhle.domain.User;
import com.thanhle.service.UserService;

@RestController
public class GatewayController {
	
	@Autowired
	InsuranceComponent insuranceComponent;
	
	@Autowired
	ClaimComponent claimComponent;
	
	@Autowired
	InsuredComponent insuredComponent;
	
	@Autowired
	UserService userService;
	
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
	
	@RequestMapping(value = "insured/savedInsured", method=RequestMethod.POST)
    public ResponseEntity<JsonNode> saveInsured(@RequestBody JsonNode json) {
    	JsonNode insured = insuredComponent.saveInsured(json);
    	return new ResponseEntity<>(insured, HttpStatus.OK);
    }
	
	@RequestMapping(value = "insured/getInsured", method = RequestMethod.GET)
	public JsonNode getInsured(Principal principal){
		String username = principal.getName();
   		User user = userService.findByUserName(username);
   		
		return insuredComponent.getInsured();
	}
	
	@RequestMapping(value = "insured/phone/{phone}", method = RequestMethod.GET)
	public JsonNode getInsuredByPhone(@PathVariable String phone, Principal principal){
		String username = principal.getName();
   		User user = userService.findByUserName(username);
   		phone = user.getMobile();
		return insuredComponent.findInsuredByPhone(phone);
	}
	
	@RequestMapping(value = "claim/phone/{phone}", method = RequestMethod.GET)
	public JsonNode getClaimByPhone(@PathVariable String phone, Principal principal){
		String username = principal.getName();
   		User user = userService.findByUserName(username);
   		phone = user.getMobile();
		return claimComponent.findClaimByPhone(phone);
	}


}
