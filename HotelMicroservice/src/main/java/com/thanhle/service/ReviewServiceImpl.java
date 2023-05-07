package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;
import com.thanhle.repository.HotelRepository;
import com.thanhle.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
    ReviewRepository reviewRepository;
	@Autowired
    HotelRepository hotelRepository;
	//@Autowired
	//Hotel hotel;
	//@Autowired
	//Review review;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HotelRepository hotelRepository) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Review saveReview(int hotelId, HotelReviewRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
       Review review = new Review();
        review.setHotel(hotel);
        review.setEmail(request.getEmail());
        review.setRating(request.getRating());
        review.setReview(request.getReview());

        return reviewRepository.save(review);
    }

    // other methods
}
