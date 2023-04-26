package com.thanhle.service;

import java.util.List;
import com.thanhle.domain.Booking;
import com.thanhle.domain.Guest;

public interface BookingService {
    //List<Booking> getAllBookings();

    public List<Booking> findByCustomerMobile(String mobile);
    public List<Booking> findByStatus(String status);
    public List<Guest> findGuestsByBookingId(int bookingId);
  
    
    //Booking getBookingById(int bookingId);
    //Booking addBooking(int hotelId, int hotelRoomId, int noRooms, Set<Guest> guests, String customerMobile,
    //String roomType, String email, float price, float discount);
    //Booking updateBookingStatus(int bookingId, String status);
    //void deleteBookingById(int bookingId);
}
