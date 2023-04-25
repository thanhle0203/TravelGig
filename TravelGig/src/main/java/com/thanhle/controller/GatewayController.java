package com.thanhle.controller;

import java.security.Principal;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.HotelComponent;
import com.thanhle.domain.User;
import com.thanhle.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SessionAttributes("user")
public class GatewayController {

    @Autowired
    HotelComponent hotelComponent;

    @Autowired
    UserService userService;
    
    @PostMapping(value = "/signup", consumes = "application/json")
    @ResponseBody
    public User saveSignup(@RequestBody User user) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getUserPassword());
            user.setUserPassword(hashedPassword);
            return userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    //@GetMapping("/save-signup")
   // public String showSignupPage() {
        //return "redirect:/login";
    //}


    @RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode searchHotel(@PathVariable String searchString, Principal principal){
		String username = principal.getName();
		System.out.println("Welcome -----------------" + username);
		return hotelComponent.findHotelBySearchText(searchString);
	}
	
	// Add this method to handle POST requests to /searchHotel endpoint
	@PostMapping(value = "/searchHotel")
	public JsonNode searchHotelPost(@RequestBody String searchString, Principal principal){
		String username = principal.getName();
		System.out.println("Welcome -----------------" + username);
		return hotelComponent.findHotelBySearchText(searchString);
	}

}
