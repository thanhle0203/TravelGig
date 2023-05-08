package com.thanhle.domain;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewId;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

	private String rating;
	private String review;
	
	public Review() {
		super();
	}

	public Review(Hotel hotel, String rating, String review) {
		super();
		this.hotel = hotel;
		this.rating = rating;
		this.review = review;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setRatingId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}