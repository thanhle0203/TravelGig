package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Hotel;
import com.thanhle.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review save(Review rating);
}
