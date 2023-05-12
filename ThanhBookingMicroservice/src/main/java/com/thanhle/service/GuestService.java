package com.thanhle.service;

import java.util.List;
import java.util.Optional;

import com.thanhle.domain.Guest;



public interface GuestService {
	public List<Guest> findAll();
	public Guest saveGuest(Guest guest);
	

}
