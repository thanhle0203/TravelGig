package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Building;
import com.thanhle.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	public Room findByRoomType(String roomType);
	
}