package com.thanhle.service;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.Document;
import com.thanhle.domain.Insured;
import com.thanhle.repository.AutoInsuranceRepository;
import com.thanhle.repository.InsuredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuredServiceImpl implements InsuredService {

    private final AutoInsuranceRepository autoInsuranceRepository;
    private final InsuredRepository insuredRepository;

    @Autowired
    public InsuredServiceImpl(AutoInsuranceRepository autoInsuranceRepository, InsuredRepository insuredRepository) {
        this.autoInsuranceRepository = autoInsuranceRepository;
        this.insuredRepository = insuredRepository;
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

}
