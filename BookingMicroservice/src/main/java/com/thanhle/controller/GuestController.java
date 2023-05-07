package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanhle.domain.Guest;
import com.thanhle.service.GuestService;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class GuestController {
	
	@Autowired
	GuestService guestService;

	@PostMapping(value = "/guests")
	@ResponseBody
	public Guest saveGuest(@RequestBody Guest guest) {
		return guestService.saveGuest(guest);
	}
	
	@GetMapping(value = "/guests")
	@ResponseBody
	public List<Guest> getAllGuests() {
	    return guestService.findAll();
	}
	

	@PutMapping(value = "/guests/{id}")
	@ResponseBody
	public Guest updateGuest(@PathVariable int id, @RequestBody Guest guest) {
		guest.setGuestId(id);
		return guestService.saveGuest(guest);
	}
	
}
