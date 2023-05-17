package com.thanhle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class WelcomeController {
	
	@GetMapping(value = "/home")
	public String homePage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	    return "home";
	}

}
