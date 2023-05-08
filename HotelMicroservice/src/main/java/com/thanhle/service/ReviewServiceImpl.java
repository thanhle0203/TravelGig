package com.thanhle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.HotelReviewRequest;
import com.thanhle.domain.Review;
import com.thanhle.repository.HotelRepository;
import com.thanhle.repository.ReviewRepository;
import com.thanhle.service.ReviewService;

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


	
	/*
	@Override
	public List<String> getReviewCommentsByHotelId(int hotelId) {
		List<String> reviewComments = new ArrayList<String>();
		List<Object[]> reviewData = reviewRepository.findReviewDataByHotelId(hotelId);
		
		for (Object[] data : reviewData) {
			String comment = data[1].toString();
			reviewComments.add(comment);
		}
		
		return reviewComments;
	}

	@Override
	public double getAverageRatingByHotelId(int hotelId) {
		List<Object[]> reviewData = reviewRepository.findReviewDataByHotelId(hotelId);
		
		if (reviewData.isEmpty()) {
			return 0;
		}
		
		double sum = 0;
		
		for (Object[] data : reviewData) {
			double rating = Double.parseDouble(data[0].toString());
			sum += rating;
		}
		
		double averageRating = sum / reviewData.size();
		
		return averageRating;
	}


	@Override
	public List<Object[]> getReviewDataByHotelId(int hotelId) {
		// TODO Auto-generated method stub
		return reviewRepository.findReviewDataByHotelId(hotelId);
	}

	*/

	

}

