package com.thanhle.service;

import com.thanhle.domain.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

 

    public List<String> findAllMakes();

    public List<String> findModelsByMake(String make);

    public List<Integer> findAllYears();

	Vehicle findByMakeAndModel(String vehicleMake, String vehicleModel);

	List<String> findAllModels();

    // Add other methods as needed
}
