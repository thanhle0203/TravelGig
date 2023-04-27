package com.thanhle.service;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Booking;
import com.thanhle.repository.BookingRepository;


@Service
public class BookingServiceImp implements BookingService {

	@Autowired
    private BookingRepository bookingRepository;

	@Override
	public List<Booking> getAll() {
		
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking b) {
		Booking booking = bookingRepository.save(b);
		return booking;
	}

	@Override
	public Booking findByCustomerMobile(String customerMobile) {

		return bookingRepository.findByCustomerMobile(customerMobile);
	}

	@Override
	public void deleteBooking(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findGuestsByBookingId(bookingId);
		
		if (optionalBooking.isPresent()) {
			bookingRepository.delete(optionalBooking.get());
		}
		else {
			throw new RuntimeException("Booking with id " + bookingId + " not found");
		}
		
	}


    
	
}
