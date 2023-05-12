package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;
import com.thanhle.repository.HotelRepository;
import com.thanhle.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Review saveReviewByHotel(int hotelId, Review re) {
        // Retrieve the hotel entity with the given hotelId
        Hotel hotel = hotelRepository.findByHotelId(hotelId);

        // Create a new review entity with the given rating and review
        Review review = new Review(hotel, re.getRating(), re.getReview());

        // Save the review using the review repository
        return reviewRepository.save(review);
    }

    @Override
    public Review saveReviews(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean isHotelExists(int hotelId) {
        return hotelRepository.existsById(hotelId);
    }
}
