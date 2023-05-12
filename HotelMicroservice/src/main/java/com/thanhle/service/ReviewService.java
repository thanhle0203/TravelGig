package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;

public interface ReviewService {
	//public Review saveReview(int hotelId, HotelReviewRequest request);

	Review saveReviews(Review review);

	boolean isHotelExists(int hotelId);

	Review saveReviewByHotel(int hotelId, Review re);

	//List<Review> getReviewsByHotelId(int hotelId);
	

}
