package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanhle.domain.Review;
import com.thanhle.repository.ReviewRepository;
import com.thanhle.service.ReviewService;

@CrossOrigin(origins = "http://localhost:8282") // Enable CORS for specific origin
@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/reviews")
    public Review saveReviews(@RequestBody Review review) {
        return reviewService.saveReviews(review);
    }

    @PostMapping("/reviews/{hotelId}")
    public ResponseEntity<?> saveReview(@PathVariable("hotelId") int hotelId,
                                        @RequestBody Review review) {
        if (!reviewService.isHotelExists(hotelId)) {
            return ResponseEntity.badRequest().body("Hotel not found");
        }
        Review savedReview = reviewService.saveReviewByHotel(hotelId, review);
        return ResponseEntity.ok(savedReview);
    }
    
    
    @GetMapping("/reviews/{hotelId}")
    public ResponseEntity<List<Review>> getReviewsByHotelId(@PathVariable("hotelId") int hotelId) {
    	List<Review> reviews = reviewRepository.findReviewDataByHotelId(hotelId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }


}
