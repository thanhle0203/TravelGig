package com.thanhle.service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;
import com.thanhle.domain.Insured;
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
        AutoPlan autoPlan = autoInsurance.getAutoPlan();
        if (autoPlan != null) {
            String planName = autoPlan.getName();
            if (planName.equalsIgnoreCase("Comprehensive Plan") || planName.equalsIgnoreCase("ComprehensivePlan")) {
                autoPlan.setType("Full Coverage");
                autoPlan.setDescription("Comprehensive coverage including collision, liability, uninsured motorist protection, medical bills, car rental, and roadside assistance. Ideal for new, high-value cars.");
                autoPlan.setBasePrice(2000);
            } else if (planName.equalsIgnoreCase("Plus Plan") || planName.equalsIgnoreCase("PlusPlan")) {
                autoPlan.setType("Partial Coverage");
                autoPlan.setDescription("Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.");
                autoPlan.setBasePrice(1500);
            } else if (planName.equalsIgnoreCase("Basic Plan") || planName.equalsIgnoreCase("BasicPlan")) {
                autoPlan.setType("Basic Coverage");
                autoPlan.setDescription("Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.");
                autoPlan.setBasePrice(1000);
            }
        }

        int collisionDeductible = autoInsurance.getCollisionDeductible();
        int uninsuredMotoristDeductible = autoInsurance.getUninsuredMotoristDeductible();

        // Calculate the total price based on the selected deductibles
        double totalPrice = autoPlan.getBasePrice();
        if (collisionDeductible == 1000 && uninsuredMotoristDeductible == 1000) {
            totalPrice *= 1.2;
        }
        else if (collisionDeductible == 1000 || uninsuredMotoristDeductible == 1000) {
            totalPrice *= 1.1;
        }

        autoInsurance.setSelected(true);
        autoInsurance.setCollisionDeductible(collisionDeductible);
        autoInsurance.setUninsuredMotoristDeductible(uninsuredMotoristDeductible);
        autoInsurance.setTotalPrice(totalPrice);

        return autoInsuranceRepository.save(autoInsurance);
    }


    @Override
    public AutoInsurance getSelectedPlanByAutoId(Long planAutoId) {
        return autoInsuranceRepository.findByAutoPlanIdAndSelectedTrue(planAutoId);
    }



}
