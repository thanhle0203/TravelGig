package com.thanhle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{

	public List<Hotel> findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(String hotelName,String address,String city,String state);
	List<Review> findByHotelId(int hotelId);
	

	
}


