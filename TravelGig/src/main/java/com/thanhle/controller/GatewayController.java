package com.thanhle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.component.HotelComponent;


@RestController
public class GatewayController {

	@Autowired
	HotelComponent hotelComponent;
	
	@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode searchHotel(@PathVariable String searchString){
		return hotelComponent.findHotelBySearchText(searchString);
	}
	
}
