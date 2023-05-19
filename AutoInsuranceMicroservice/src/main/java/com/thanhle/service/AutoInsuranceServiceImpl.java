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
    public AutoInsurance saveSelectedPlan(AutoInsurance autoInsurance, AutoPlan autoPlan, int collisionDeductible, int uninsuredMotoristDeductible) {
        // First, calculate the total price based on the selected deductibles
        double totalPrice = autoPlan.getBasePrice();
        if (collisionDeductible == 1000 && uninsuredMotoristDeductible == 1000) {
            // Both deductibles are 1000, so we increase the price by 1.2
            totalPrice *= 1.2;
        } else if (collisionDeductible == 1000 || uninsuredMotoristDeductible == 1000) {
            // Only one deductible is 1000, so we increase the price by 1.1
            totalPrice *= 1.1;
        }

        // Set the total price of the auto insurance
        autoInsurance.setTotalPrice(totalPrice);
        
        // Set the selected AutoPlan
        autoInsurance.setAutoPlan(autoPlan);

        // Then, save the auto insurance and return it
        return autoInsuranceRepository.save(autoInsurance);
    }
}
