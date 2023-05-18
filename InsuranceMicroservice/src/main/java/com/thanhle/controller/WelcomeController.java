package com.thanhle.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	    return "auto";
	}
	
	@RequestMapping(value = "/insurerInfo")
	public String insurerInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	    return "insurerInfo";
	}


	@GetMapping(value = "/autoInsurancePlans")
	public String autoInsurancePlans(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoInsurancePlans";
	}
	
	@GetMapping(value = "/autoConfirmationPlan")
	public String autoConfirmationPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "autoConfirmationPlan";
	}

}
