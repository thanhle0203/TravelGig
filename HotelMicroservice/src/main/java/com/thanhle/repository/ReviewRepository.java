package com.thanhle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

import jakarta.persistence.Tuple;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review save(Review rating);
	
	@Query("SELECT r.rating, r.review FROM Review r WHERE r.hotel.hotelId = ?1")
	List<Object[]> findReviewDataByHotelId(int hotelId);


}
