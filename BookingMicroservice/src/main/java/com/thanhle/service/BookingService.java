package com.thanhle.service;

import java.util.List;
import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;

public interface BookingService {
    List<Booking> getAll();
	public Booking saveBooking(Booking booking);
	public Booking findByCustomerMobile(String customerMobile);
	public void deleteBooking(int bookingId);
}
