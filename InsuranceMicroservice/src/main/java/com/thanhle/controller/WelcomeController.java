package com.thanhle.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WelcomeController {
	@GetMapping(value = "/home")
	public String myBookings(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		
	    return "home";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome(Principal principal, Model model) {
		if (principal != null) {
			System.out.print("Welcome to Controller: " + principal.getName());
			model.addAttribute("username", principal.getName());
		}
		else {
			model.addAttribute("user", null);
		}
		return "insurance";
	}
	
	@RequestMapping(value = "/auto")
	public String auto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	    return "vehicleInfo";
	}
	
	@RequestMapping(value = "/driverInfo")
	public String insurerInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	    return "driverInfo";
	}


	@GetMapping(value = "/autoInsurancePlans")
	public String autoInsurancePlans(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoInsurancePlans";
	}
	
	@GetMapping(value = "/autoInsurancePlan")
	public String autoInsurancePlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoInsurancePlan";
	}
	
	@GetMapping(value = "/autoConfirmationPlan")
	public String autoConfirmationPlans(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoConfirmationPlan";
	}
	
	
	@PostMapping(value = "/autoConfirmationPlan")
	public String autoConfirmationPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoConfirmationPlan";
	}
	
	@PostMapping(value = "/autoConfirmation")
	public String autoConfirmation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoConfirmation";
	}
	
	@GetMapping(value = "/autoConfirmation")
	public String autoConfirmations(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoConfirmation";
	}

}
