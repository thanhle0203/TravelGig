package com.thanhle.domain;

import jakarta.persistence.*;


@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewId;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;
	
	
	private String email;
	private double rating;
	private String review;
	
	public Review() {
		super();
	}

	public Review(Hotel hotel, String email, double rating, String review) {
		super();
		this.hotel = hotel;
		this.email = email;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}
