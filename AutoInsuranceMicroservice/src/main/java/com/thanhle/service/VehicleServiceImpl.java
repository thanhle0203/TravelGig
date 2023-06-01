package com.thanhle.service;

import com.thanhle.domain.Vehicle;
import com.thanhle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<String> findAllMakes() {
        return vehicleRepository.findAllMakes();
    }

    @Override
    public List<String> findModelsByMake(String make) {
        return vehicleRepository.findModelsByMake(make);
    }

    @Override
    public List<Integer> findAllYears() {
        return vehicleRepository.findAllYears();
    }

	
	
	@Override
    public Vehicle findByMakeAndModel(String vehicleMake, String vehicleModel) {
        return vehicleRepository.findByMakeAndModel(vehicleMake, vehicleModel);
    }

	@Override
	public List<String> findAllModels() {
		// TODO Auto-generated method stub
		return vehicleRepository.findByModels();
	}

	@Override
	public Vehicle findByMakeAndModelAndYear(String vehicleMake, String vehicleModel, int vehicleYear) {
		// TODO Auto-generated method stub
		return vehicleRepository.findByMakeAndModelAndYear(vehicleMake, vehicleModel, vehicleYear);
	}

    
    // Implement other methods as needed
}
