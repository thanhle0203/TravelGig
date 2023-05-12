package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

public interface HotelService {
	public List<Hotel> searchHotel(String searchString);

	public Hotel getHotelById(int hotelId);

	Hotel saveReviewByHotelId(int hotelId, Review review);
	
	
}
