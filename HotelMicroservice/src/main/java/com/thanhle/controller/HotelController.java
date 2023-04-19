package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhle.domain.Hotel;
import com.thanhle.service.IHotelService;

@RestController
public class HotelController {

	@Autowired
	IHotelService hotelService;
	
	@CrossOrigin(origins = "http://localhost:8080") // Enable CORS for specific origin
	@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public List<Hotel> searchHotel(@PathVariable String searchString){
		return hotelService.searchHotel(searchString);
	}
	
}