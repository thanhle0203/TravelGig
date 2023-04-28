package com.thanhle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Guest;
import com.thanhle.repository.GuestRepository;

@Service
public class GuestServiceImp implements GuestService {
	@Autowired
	GuestRepository guestRepository;

	@Override
	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	@Override
	public Guest saveGuest(Guest g) {
		Guest guest = guestRepository.save(g);
		return guest;
	}

	//@Override
	//public void deleteUserById(long uId) {
		//guestRepository.deleteById(uId);
		
	//}

	//@Override
	//public Guest findByUserId(long uId) {
		//Optional<Guest> g = guestRepository.findById(uId);
		//if (g.isPresent()) {
			//return g.get();
		//}else {
			//return null;
		//}
		
	//}

	@Override
	public Guest findByFirstName(String firstName) {
		
		return guestRepository.findByFirstName(firstName);
	}

	@Override
	public void deleteGuest(int guestId) {
		//Guest optionalGuest = guestRepository.findByGuestId(guestId);
	    //if (optionalGuest.isPresent()) {
	        //Guest guest = optionalGuest.get();
	        //guestRepository.delete(guest);
	    //} else {
	       // throw new RuntimeException("Guest with id " + guestId + " not found");
	    //}
		
	}

	@Override
	public Guest getGuestById(int guestId) {
		// TODO Auto-generated method stub
		return guestRepository.findByGuestId(guestId);
	}

}
