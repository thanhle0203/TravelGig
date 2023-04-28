package com.thanhle.service;

import java.util.List;
import java.util.Optional;

import com.thanhle.domain.Guest;



public interface GuestService {
	public List<Guest> findAll();
	public Guest saveGuest(Guest guest);
	//public void deleteUserById(long uId);
	//public Guest findByUserId(long uId);
	public Guest findByFirstName(String firstName);
	public void deleteGuest(int id);
	public Guest getGuestById(int guestId);

}
