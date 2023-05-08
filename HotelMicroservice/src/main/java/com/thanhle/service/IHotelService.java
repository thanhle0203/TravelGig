package com.thanhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.controller.ReviewController;
import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;
import com.thanhle.repository.HotelRepository;
import com.thanhle.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class IHotelService implements HotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired 
	ReviewService reviewService;
	
	@Autowired
	ReviewController reviewController;
	

	@Override
	public List<Hotel> searchHotel(String searchString) {
		searchString = "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(searchString, searchString, searchString, searchString);
	}
	
	@Override
	public Hotel getHotelById(int hotelId) {
	
	   
	    return hotelRepository.findByHotelId(hotelId);
	}

	@Override
	public Hotel saveReviewByHotelId(int hotelId, Review review) {
	    Hotel hotel = hotelRepository.findByHotelId(hotelId);
	    // Update the starRating based on the new review
	    List<Review> reviewData = reviewRepository.findReviewDataByHotelId(hotelId);
	    double sum = 0.0;
	    for (Review data : reviewData) {
	        sum += Double.parseDouble(data.getRating());
	    }
	    double averageRating = Math.ceil(sum / reviewData.size());
	    int starRating = (int)averageRating;
	    // Save the review and update the hotel
	    review.setHotel(hotel);
	    reviewRepository.save(review);
	    hotel.setStarRating(starRating);
	    hotelRepository.save(hotel);
	    return hotel;
	}

}
