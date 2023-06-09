package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import com.thanhle.domain.Trainer;
import com.thanhle.repository.TrainerRepository;

@Service
public class ITrainerService implements TrainerService{

	@Autowired
	@Qualifier("trainerRepository")
	TrainerRepository trainerRepository;

	
	@Override
	public Trainer saveTrainer(Trainer trainer) {
		return trainerRepository.save(trainer);
		
	}

	public Trainer getTrainerByName(String autoName) {
		// TODO Auto-generated method stub
		return trainerRepository.findTrainerByTrainerName(autoName);
	}

	public List<Trainer> getAllTrainers() {
		// TODO Auto-generated method stub
		return trainerRepository.findAll();
	}

	

}
