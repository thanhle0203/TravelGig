package com.thanhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Building;
import com.thanhle.domain.Room;
import com.thanhle.repository.BuildingRepository;

@Service
public class IBuildingService implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Building saveOrUpdateBuilding(Building building) {
        // Check if the building name already exists in the database
        Building existingBuilding = buildingRepository.findByBuName(building.getBuName());
        if (existingBuilding == null) {
            // Save the new building with its associated room types and sizes
            return buildingRepository.save(building);
        } else {
            // Update the existing building with its associated room types and sizes
            List<Room> existingRooms = (List<Room>) existingBuilding.getRoomList();
            List<Room> updatedRooms = (List<Room>) building.getRoomList();
            for (Room updatedRoom : updatedRooms) {
                boolean roomExists = false;
                for (Room existingRoom : existingRooms) {
                    if (updatedRoom.getRoomType().equals(existingRoom.getRoomType())) {
                        existingRoom.setRoomSize(updatedRoom.getRoomSize());
                        roomExists = true;
                        break;
                    }
                }
                if (!roomExists) {
                    existingRooms.add(updatedRoom);
                }
            }
            existingBuilding.setRoomList(existingRooms);
            return buildingRepository.save(existingBuilding);
        }
    }

    @Override
    public Building getBuildingByName(String buName) {
        return buildingRepository.findByBuName(buName);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public void saveBuilding(Building building) {
        buildingRepository.save(building);
    }

}
