package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Building;

public interface BuildingService {
	public Building saveOrUpdateBuilding(Building building);
	public Building getBuildingByName(String buName);
	public List<Building> getAllBuildings();
	public void saveBuilding(Building building);
}
