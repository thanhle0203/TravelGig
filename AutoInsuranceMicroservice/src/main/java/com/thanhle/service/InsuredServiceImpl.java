package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Insured;
import com.thanhle.repository.InsuredRepository;

import java.util.List;

@Service
public class InsuredServiceImpl implements InsuredService {
    private final InsuredRepository insuredRepository;

    @Autowired
    public InsuredServiceImpl(InsuredRepository insuredRepository) {
        this.insuredRepository = insuredRepository;
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
