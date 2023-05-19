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
		return autoPlanRepository.save(autoPlan);
	}

    @Override
    public void deletePlan(Long id) {
        autoPlanRepository.deleteById(id);
    }

	
}
