package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Automobile;

public interface AutomobileService {

	public Automobile saveAutomobile(Automobile automobile);
	public Automobile getAuto(String autoName);
	public List<Automobile> getAllAuto();
}
