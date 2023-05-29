package com.thanhle.service;

import java.util.List;

import com.thanhle.domain.Insured;

public interface InsuredService {
    Insured saveInsured(Insured insured);
    List<Insured> getAllInsured();
	//Insured saveInsured(Insured insured, long autoInsuranceId);
	Insured getInsuredByEmail(String email);
}
