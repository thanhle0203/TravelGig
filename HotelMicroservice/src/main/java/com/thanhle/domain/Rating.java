package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ratingId;
	private int bookingId;
	private String email;
	private double ratings;
	private String reviews;
	
	public Rating(int ratingId, int bookingId, String email, double ratings, String reviews) {
		this.ratingId = ratingId;
		this.bookingId = bookingId;
		this.email = email;
		this.ratings = ratings;
		this.reviews = reviews;
		this.email = email;
	}
	
	public Rating() {
		
	}
}
