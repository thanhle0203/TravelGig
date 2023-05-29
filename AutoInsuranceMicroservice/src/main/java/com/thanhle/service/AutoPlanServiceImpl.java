package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.AutoPlan;
import com.thanhle.repository.AutoPlanRepository;

import java.util.List;

@Service
public class AutoPlanServiceImpl implements AutoPlanService {

    @Autowired
    private AutoPlanRepository autoPlanRepository;

    @Override
    public List<AutoPlan> getAllPlans() {
        return autoPlanRepository.findAll();
    }

    @Override
    public AutoPlan getPlanById(Long id) {
        return autoPlanRepository.findById(id).orElse(null);
    }

    @Override
	public AutoPlan savePlan(AutoPlan autoPlan) {
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
    	
		return autoPlanRepository.save(autoPlan);
	}
    
    @Override
    public AutoPlan findByName(String name) {
        AutoPlan autoPlan = autoPlanRepository.findByName(name);

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

        return autoPlan;
    }


    @Override
    public void deletePlan(Long id) {
        autoPlanRepository.deleteById(id);
    }

	
}
