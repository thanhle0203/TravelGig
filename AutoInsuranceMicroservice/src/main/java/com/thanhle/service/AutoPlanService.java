package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.AutoPlan;

public interface AutoPlanService {
	public List<AutoPlan> getAllPlans();
	public AutoPlan getPlanById(Long id);
	public AutoPlan savePlan(AutoPlan autoPlan);
	public void deletePlan(Long id);
	public AutoPlan findByName(String name);
	

}
