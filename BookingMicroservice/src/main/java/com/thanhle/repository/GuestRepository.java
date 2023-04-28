package com.thanhle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	Guest findByFirstName(String firstName);

	Guest findByGuestId(int guestId);
}
