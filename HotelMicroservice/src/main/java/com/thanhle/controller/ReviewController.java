package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;
import com.thanhle.service.ReviewService;

@CrossOrigin(origins = "http://localhost:8282") // Enable CORS for specific origin
@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
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



    /*
    @GetMapping("/{hotelId}/comments")
    public ResponseEntity<List<String>> getReviewCommentsByHotelId(@PathVariable("hotelId") int hotelId) {
        List<String> comments = reviewService.getReviewCommentsByHotelId(hotelId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}/rating")
    public ResponseEntity<Double> getAverageRatingByHotelId(@PathVariable("hotelId") int hotelId) {
        double averageRating = reviewService.getAverageRatingByHotelId(hotelId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @PostMapping("/reviews/{hotelId}")
    public Review saveReview(@PathVariable("hotelId") int hotelId,
                                              @RequestBody Review request) {
   
        return reviewService.saveReview(hotelId, request);
    }
    
    */
    

}

