package com.thanhle.service;

import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;

public interface ReviewService {
	public Review saveReview(int hotelId, HotelReviewRequest request);
}
