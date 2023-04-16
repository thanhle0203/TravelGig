package com.thanhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Hotel;
import com.thanhle.repository.HotelRepository;

@Service
public class IHotelService implements HotelService{

	@Autowired
	HotelRepository hotelRepository;

	@Override
	public List<Hotel> searchHotel(String searchString) {
		searchString = "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(searchString, searchString, searchString, searchString);
	}
	
	
	
}
