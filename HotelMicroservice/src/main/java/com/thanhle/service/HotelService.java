package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Hotel;

public interface HotelService {
	public List<Hotel> searchHotel(String searchString);
}
