package com.thanhle.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String welcomepage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
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
	
	@GetMapping(value = "/document")
	public String submitDocument(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "document";
	}
	
	@GetMapping(value = "/insuredInfo")
	public String insuredInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "insuredInfo";
	}
	
	@GetMapping(value = "/insured")
	public String insured(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "insured";
	}
	
	@PostMapping(value = "/insured")
	public String insureds(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "insured";
	}
	
	@PostMapping(value = "/payment")
	public String payment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "payment";
	}
	
	@GetMapping(value = "/payment")
	public String payments(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	   return "payment";
	}
	
	/*
	@GetMapping(value = "/admin")
	public String admin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,  Model model) {
		
	   return "admin";
	}
	*/
	
	/*
	@GetMapping(value = "/admin")
	public String admin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Principal principal) {
	    String loggedInUsername = principal.getName();
	    // Check if the logged-in user has the ADMIN role
	    // You can replace this condition with your actual logic to check the user's role
	    if (loggedInUsername != null && loggedInUsername.equals("admin")) {
	        return "admin";
	    } else {
	        // Redirect or show an error page for unauthorized access
	        return "redirect:/accessDeniedPage";
	    }
	}
	*/
	
	@GetMapping(value = "/admin")
	public String admin() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getAuthorities().stream()
	            .anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
	        return "admin";
	    } else {
	        // Redirect or show an error page for unauthorized access
	        return "redirect:/accessDeniedPage";
	    }
	}

	
	@GetMapping("/manage-insurance")
	public String manageInsurance(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "manage-insurance";
	}
	
	@GetMapping("/blog-insurance")
	public String blogInsurance(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "blog-insurance";
	}
	
	@GetMapping("/contact")
	public String contactInsurance(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "contact";
	}

	@GetMapping("/claim")
	public String claimInsurance(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "claim";
	}
	
	@GetMapping("/manage-claim")
	public String claimInsurances(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "manage-claim";
	}
	
	@GetMapping("/paymentConfirmation")
	public String paymentConfirmation(Model model) {
	    // Add any necessary data to the model for rendering in the view
	    return "paymentConfirmation";
	}


 
 
}
