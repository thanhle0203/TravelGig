package com.thanhle.service;

import java.util.List;
import java.util.Set;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;

public interface BookingService {
    List<Booking> getAll();
	//public Booking saveBooking(Booking booking, Guest guest);
	public Booking findByCustomerMobile(String customerMobile);
	public void deleteBooking(int bookingId);
	Booking saveBookings(Booking booking, Set<Guest>  guest);
	Booking saveBooking(Booking booking);
	public Booking findByBookingId(int bId);
}
