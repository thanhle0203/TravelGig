package com.thanhle.service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;

import java.util.List;

public interface AutoInsuranceService {
    List<AutoInsurance> getAllAutoInsurances();
    AutoInsurance getAutoInsuranceById(Long id);
    AutoInsurance saveAutoInsurance(AutoInsurance autoInsurance);
    void deleteAutoInsurance(Long id);
    List<AutoInsurance> getSelectedPlan(); 
	AutoInsurance saveOrUpdate(AutoInsurance autoInsurance);
	AutoInsurance saveSelectedPlan(AutoInsurance autoInsurance);

	AutoInsurance getSelectedPlanByAutoId(Long planAutoId);
}
