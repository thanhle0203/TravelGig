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

	@PostMapping(value = "/guest")
	@ResponseBody
	public Guest saveGuest(@RequestBody Guest guest) {
		return guestService.saveGuest(guest);
	}
	
	@GetMapping(value = "/guest")
	@ResponseBody
	public List<Guest> getAllGuests() {
	    return guestService.findAll();
	}
	
	@DeleteMapping(value = "/guest/{id}")
	@ResponseBody
	public void deleteGuest(@PathVariable int id) {
		guestService.deleteGuest(id);
	}

	@PutMapping(value = "/guest/{id}")
	@ResponseBody
	public Guest updateGuest(@PathVariable int id, @RequestBody Guest guest) {
		guest.setGuestId(id);
		return guestService.saveGuest(guest);
	}
	
}
