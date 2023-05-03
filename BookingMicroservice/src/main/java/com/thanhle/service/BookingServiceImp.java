package com.thanhle.service;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;
import com.thanhle.repository.BookingRepository;
import java.util.*
;

@Service
public class BookingServiceImp implements BookingService {

	@Autowired
    private BookingRepository bookingRepository;

	@Override
	public List<Booking> getAll() {
		
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBookings(Booking b, Set<Guest> g) {
		b.setGuests(g);
		Booking booking = bookingRepository.save(b);
		return booking;
	}

	@Override
	public List<Booking> findByCustomerMobile(String customerMobile) {
		return bookingRepository.findByCustomerMobile(customerMobile);
	}
	
	@Override
	public Booking findByBookingId(int bId) {
		Optional<Booking> b = bookingRepository.findByBookingId(bId);
		if(b.isPresent()) {
			return b.get();
		}else {
			return null;
		}
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


    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking saveUpcomingBooking(Booking booking) {
        booking.setStatus("upcoming");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking saveCompletedBooking(Booking booking) {
        booking.setStatus("completed");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking saveCancelledBooking(Booking booking) {
        booking.setStatus("cancelled");
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getUpcomingBookings() {
        return bookingRepository.findByStatusAndCheckInDateAfter("upcoming", new Date());
    }

    @Override
    public List<Booking> getCompletedBookings() {
        return bookingRepository.findByStatusAndCheckInDateBefore("upcoming", new Date());
    }

    @Override
    public List<Booking> getCancelledBookings() {
        return bookingRepository.findByStatus("cancelled");
    }

	//@SuppressWarnings("unchecked")
	//@Override
	//public List<Booking> getBookingsByMobileNo(String customerMobile) {
		// TODO Auto-generated method stub
		//return (List<Booking>) bookingRepository.findByCustomerMobile(customerMobile);;
	//}


    
	
}
