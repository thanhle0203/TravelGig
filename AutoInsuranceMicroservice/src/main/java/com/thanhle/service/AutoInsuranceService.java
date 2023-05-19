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
	AutoInsurance saveSelectedPlan(AutoInsurance autoInsurance, AutoPlan autoPlan, int collisionDeductible,
			int uninsuredMotoristDeductible);
	AutoInsurance saveOrUpdate(AutoInsurance autoInsurance);
}
