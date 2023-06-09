package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Trainer;

public interface TrainerService {

	public Trainer saveTrainer(Trainer trainer);
	public Trainer getTrainerByName(String autoName);
	//public Trainer getTrainerById(Long id);
	public List<Trainer> getAllTrainers();
}
