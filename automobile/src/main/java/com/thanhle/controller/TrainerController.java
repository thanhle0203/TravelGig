package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhle.domain.Trainer;
import com.thanhle.service.ITrainerService;

@RestController
public class TrainerController {

	@Autowired
	ITrainerService trainerService;
	
	@RequestMapping(value = "/saveTrainer",method = RequestMethod.POST)
	public Trainer saveTrainer(@RequestBody Trainer trainer) {
		return trainerService.saveTrainer(trainer);
	}
	
	@RequestMapping(value = "/getTrainer/{autoName}",method = RequestMethod.GET)
	public Trainer getTrainerByName(@PathVariable String autoName) {
		return trainerService.getTrainerByName(autoName);
	}
	
	@RequestMapping(value = "/getAllTrainers",method = RequestMethod.GET)
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainers();
	}
	
}