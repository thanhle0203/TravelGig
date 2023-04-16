package com.thanhle.service;

import java.util.List;
import com.thanhle.domain.Automobile;
import com.thanhle.repository.AutomobileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IAutomobileService implements AutomobileService{

	@Autowired
	AutomobileRepository automobileRepository;
	
	@Override
	public Automobile saveAutomobile(Automobile automobile) {
		return automobileRepository.save(automobile);
		
	}

	public Automobile getAuto(String autoName) {
		// TODO Auto-generated method stub
		return automobileRepository.findByName(autoName);
	}

	public List<Automobile> getAllAuto() {
		// TODO Auto-generated method stub
		return automobileRepository.findAll();
	}

}