package com.thanhle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

import jakarta.persistence.Tuple;

@Repository
@CrossOrigin(origins = "http://localhost:8282") // Enable CORS for specific origin
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review save(Review rating);

	
	@Query("SELECT r FROM Review r WHERE r.hotel.hotelId = :hotelId")
	List<Review> findReviewDataByHotelId(int hotelId);
	
	List<Review> findByHotelHotelId(int hotelId);
	
	
	
}
