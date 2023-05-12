package com.thanhle.service;

import java.util.List;
import java.util.Set;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;

public interface BookingService {
    List<Booking> getAll();
	public List<Booking> findByCustomerMobile(String customerMobile);
	Booking saveBooking(Booking booking);
	public List<Booking> findByBookingId(int bId);
	
	Booking saveCancelledBooking(Booking booking);
	List<Booking> getCancelledBookings();
	List<Booking> getUpcomingdBookings();
	List<Booking> getCompleteddBookings();
}
