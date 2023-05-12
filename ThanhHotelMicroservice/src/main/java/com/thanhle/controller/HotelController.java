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
import com.thanhle.domain.Review;
import com.thanhle.service.IHotelService;
import com.thanhle.service.ReviewService;

@CrossOrigin(origins = "http://localhost:8282") // Enable CORS for specific origin
@RestController
public class HotelController {

    @Autowired
    IHotelService hotelService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/searchHotel/{searchText}", method = RequestMethod.GET)
    public List<Hotel> searchHotel(@PathVariable String searchText) {
        return hotelService.searchHotel(searchText);
    }

    @PostMapping("/hotel/reviews/{hotelId}")
    public Hotel saveReviewByHotelId(@PathVariable Integer hotelId, @RequestBody Review review) {
        return hotelService.saveReviewByHotelId(hotelId, review);
    }
}
