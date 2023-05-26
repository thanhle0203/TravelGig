package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.Insured;
import com.thanhle.repository.AutoInsuranceRepository;
import com.thanhle.repository.InsuredRepository;

import java.util.List;

@Service
public class InsuredServiceImpl implements InsuredService {
    
    @Autowired
    private AutoInsuranceRepository autoInsuranceRepository;

    @Autowired
    private InsuredRepository insuredRepository;

    
    @Override
    public Insured saveInsured(Insured insured, long autoInsuranceId) {
        AutoInsurance autoInsurance = autoInsuranceRepository.findById(autoInsuranceId).orElse(null);
        insured.setAutoInsurance(autoInsurance);
        autoInsurance.setInsured(insured); // Set the insured for the autoInsurance
        autoInsuranceRepository.save(autoInsurance); // Save the autoInsurance
        return insuredRepository.save(insured); // Save the insured
    }



    @Override
    public Insured saveInsured(Insured insured) {
        return insuredRepository.save(insured);
    }


    @Override
    public List<Insured> getAllInsured() {
        return insuredRepository.findAll();
    }

}
