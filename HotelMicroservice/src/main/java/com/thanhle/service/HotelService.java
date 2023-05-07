package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

public interface HotelService {
	public List<Hotel> searchHotel(String searchString);

	List<Review> getRatings(int hotelId);
}
