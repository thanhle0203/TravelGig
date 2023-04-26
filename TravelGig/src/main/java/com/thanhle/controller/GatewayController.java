package com.thanhle.controller;

import java.security.Principal;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.HotelComponent;
import com.thanhle.domain.User;
import com.thanhle.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SessionAttributes("user")
public class GatewayController {

    @Autowired
    HotelComponent hotelComponent;

    @Autowired
    UserService userService;
    
   
    
    //@PostMapping(value = "/signup")
    //@ResponseBody
    //public String saveSignup(@RequestBody User user) {
        //try {
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //String hashedPassword = passwordEncoder.encode(user.getUserPassword());
            //user.setUserPassword(hashedPassword);
            //userService.save(user);
            //return "redirect:/login";
        //} catch (Exception e) {
            //e.printStackTrace();
            //return "signup";
        //}
    //}
    
    //@PostMapping("/signup")
    //public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        //User existingUser = userService.findByEmail(user.getEmail());
        //if (existingUser != null) {
            //bindingResult.rejectValue("email", "error.user", "This email already exists!");
            //return "signup";
        //}
        //if (bindingResult.hasErrors()) {
            //return "signup";
        //} else {
            //userService.save(user);
            //return "redirect:/login?success=true";
        //}
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
