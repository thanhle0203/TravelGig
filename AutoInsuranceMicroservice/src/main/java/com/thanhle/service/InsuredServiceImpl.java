package com.thanhle.service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.Claim;
import com.thanhle.domain.Document;
import com.thanhle.domain.Insured;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.AutoInsuranceRepository;
import com.thanhle.repository.InsuredRepository;
import com.thanhle.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsuredServiceImpl implements InsuredService {

    private final AutoInsuranceRepository autoInsuranceRepository;
    private final InsuredRepository insuredRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public InsuredServiceImpl(AutoInsuranceRepository autoInsuranceRepository, InsuredRepository insuredRepository, VehicleRepository vehicleRepository) {
        this.autoInsuranceRepository = autoInsuranceRepository;
        this.insuredRepository = insuredRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Insured saveInsured(Insured insured) {
        AutoInsurance autoInsurance = insured.getAutoInsurance();
        if (autoInsurance != null) {
            AutoInsurance existingAutoInsurance = autoInsuranceRepository.findById(autoInsurance.getId()).orElse(null);
            if (existingAutoInsurance != null) {
                // If an existing AutoInsurance is associated with the insured, update its fields
                existingAutoInsurance.setCollisionDeductible(autoInsurance.getCollisionDeductible());
                existingAutoInsurance.setUninsuredMotoristDeductible(autoInsurance.getUninsuredMotoristDeductible());
                existingAutoInsurance.setAutoPlan(autoInsurance.getAutoPlan());
                existingAutoInsurance.setTotalPrice(autoInsurance.getTotalPrice());
                // Set any other fields that need to be updated
                insured.setAutoInsurance(existingAutoInsurance);
            } else {
                // If there was no existing AutoInsurance, associate the new AutoInsurance with the insured 
                insured.setAutoInsurance(autoInsurance);
            }
        }
        
     // Retrieve the existing Vehicle associated with the insured
        Vehicle vehicle = insured.getVehicle();

        if (vehicle != null) {
            Vehicle existingVehicle = vehicleRepository.findById(vehicle.getId()).orElse(null);
            if (existingVehicle != null) {
                // If an existing Vehicle is associated with the insured, update its fields
                existingVehicle.setMake(vehicle.getMake());
                existingVehicle.setModel(vehicle.getModel());
                existingVehicle.setVin(vehicle.getVin());
                existingVehicle.setYear(vehicle.getYear());
                // Set any other fields that need to be updated
                insured.setVehicle(existingVehicle);
            } else {
                // If there was no existing Vehicle, associate the new Vehicle with the insured
                insured.setVehicle(vehicle);
            }
        }
        
        

        return insuredRepository.save(insured);

    }

    @Override
    public List<Insured> getAllInsured() {
        return insuredRepository.findAll();
    }
    
    @Override
    public Insured getInsuredByEmail(String email) {
        return insuredRepository.findByEmail(email);
    }
    
    @Override
    public Insured getInsuredByPhone(String phone) {
        return insuredRepository.findByPhone(phone);
    }

	@Override
	public void updateInsuredStatus(Long insuredId, String status) {
		Optional<Insured> optionalInsured = insuredRepository.findById(insuredId);
        if (optionalInsured.isPresent()) {
            Insured insured = optionalInsured.get();
            insured.setStatus(status);
            insuredRepository.save(insured);
        }
		
	}

}
