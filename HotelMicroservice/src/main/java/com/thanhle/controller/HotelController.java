package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;
import com.thanhle.service.IHotelService;
import com.thanhle.service.ReviewService;

@RestController
public class HotelController {

	@Autowired
	IHotelService hotelService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	Review review;
	
	@CrossOrigin(origins = "http://localhost:8282") // Enable CORS for specific origin
	@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public List<Hotel> searchHotel(@PathVariable String searchString){
		return hotelService.searchHotel(searchString);
	}
	
	 @PostMapping("/reviews/{hotelId}")
	    public ResponseEntity<String> createRating(@PathVariable int hotelId, @RequestBody HotelReviewRequest request) {
	        review = reviewService.saveReview(hotelId, request);
	        return ResponseEntity.ok("Rating created with ID: " + review.getReviewId());
	    }
	
}