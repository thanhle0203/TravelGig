package com.thanhle.domain;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Building {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int buId;
	private String buName;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Room> roomList;


	public int getBuId() {
		return buId;
	}

	public void setBuId(int buId) {
		this.buId = buId;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> existingRooms) {
		this.roomList = (List<Room>) existingRooms;
	}
	
}
