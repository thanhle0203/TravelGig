package com.thanhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;
import com.thanhle.repository.HotelRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class IHotelService implements HotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

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
        double totalRating = hotel.getStarRating() * hotel.getTimesBooked();
        totalRating += review.getRating();
        hotel.setTimesBooked(hotel.getTimesBooked() + 1);
        hotel.setStarRating(totalRating / hotel.getTimesBooked());

        // Save the review and update the hotel
        //hotel.setStarRating(totalRating);
        
        return hotel;
       
    }
}
