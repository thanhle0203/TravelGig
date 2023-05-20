package com.thanhle.service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;
import com.thanhle.repository.AutoInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutoInsuranceServiceImpl implements AutoInsuranceService {
    @Autowired
    private AutoInsuranceRepository autoInsuranceRepository;
    
    // Removed Autowiring of AutoPlan here

    @Override
    public List<AutoInsurance> getAllAutoInsurances() {
        return autoInsuranceRepository.findAll();
    }

    @Override
    public AutoInsurance getAutoInsuranceById(Long id) {
        return autoInsuranceRepository.findById(id).orElse(null);
    }

    @Override
    public AutoInsurance saveAutoInsurance(AutoInsurance autoInsurance) {
        return autoInsuranceRepository.save(autoInsurance);
    }
    
    @Override
    public void deleteAutoInsurance(Long id) {
        autoInsuranceRepository.deleteById(id);
    }
    
    
    @Override
    public List<AutoInsurance> getSelectedPlan() {
        return autoInsuranceRepository.findBySelectedTrue();
    }
    
    @Override
    public AutoInsurance saveOrUpdate(AutoInsurance autoInsurance) {
        return autoInsuranceRepository.save(autoInsurance);
    }
    
    @Override
    public AutoInsurance saveSelectedPlan(AutoInsurance autoInsurance) {
        // Extract the AutoPlan, collisionDeductible, and uninsuredMotoristDeductible from the autoInsurance object
        AutoPlan autoPlan = autoInsurance.getAutoPlan();
        if (autoPlan != null) {
            if (autoPlan.getName().equals("Comprehensive Plan")) {
                autoPlan.setType("Full Coverage");
                autoPlan.setDescription("Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.");
                autoPlan.setBasePrice(2000);
            } else if (autoPlan.getName().equals("Plus Plan")) {
                autoPlan.setType("Partial Coverage");
                autoPlan.setDescription("Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.");
                autoPlan.setBasePrice(1500);
            } else if (autoPlan.getName().equals("Basic Plan")) {
                autoPlan.setType("Basic Coverage");
                autoPlan.setDescription("Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.");
                autoPlan.setBasePrice(1000);
            }
        }
        
        int collisionDeductible = autoInsurance.getCollisionDeductible();
        int uninsuredMotoristDeductible = autoInsurance.getUninsuredMotoristDeductible();

        // First, calculate the total price based on the selected deductibles
        double totalPrice = autoPlan.getBasePrice();
        if (collisionDeductible == 1000 && uninsuredMotoristDeductible == 1000) {
            // Both deductibles are 1000, so we increase the price by 1.2
            totalPrice *= 1.2;
        } else if (collisionDeductible == 1000 || uninsuredMotoristDeductible == 1000) {
            // Only one deductible is 1000, so we increase the price by 1.1
            totalPrice *= 1.1;
        }

        autoInsurance.setCollisionDeductible(collisionDeductible);
        autoInsurance.setUninsuredMotoristDeductible(uninsuredMotoristDeductible);
        autoInsurance.setSelected(true);

        // Set the total price of the auto insurance
        autoInsurance.setTotalPrice(totalPrice);

        // Then, save the auto insurance and return it
        return autoInsuranceRepository.save(autoInsurance);
    }
    
    @Override
    public AutoInsurance getSelectedPlanByAutoId(Long planAutoId) {
        return autoInsuranceRepository.findByAutoPlanIdAndSelectedTrue(planAutoId);
    }



}
