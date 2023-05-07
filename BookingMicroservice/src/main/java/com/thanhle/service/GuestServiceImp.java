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
	//public Guest findByUserId(long uId) {
		//Optional<Guest> g = guestRepository.findById(uId);
		//if (g.isPresent()) {
			//return g.get();
		//}else {
			//return null;
		//}
		
	//}

	

}
