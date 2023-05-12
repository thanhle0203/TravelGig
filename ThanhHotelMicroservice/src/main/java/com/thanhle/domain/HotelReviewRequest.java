package com.thanhle.domain;

public class HotelReviewRequest {

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

    private double rating;
    private String review;
    
    
}
