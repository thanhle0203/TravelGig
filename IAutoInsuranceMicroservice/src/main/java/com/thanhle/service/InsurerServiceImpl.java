package com.thanhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.thanhle.domain.Insurer;
import com.thanhle.repository.InsurerRepository;

@Service
public class InsurerServiceImpl implements InsurerService {
	@Autowired
	InsurerRepository insurerRepository;

	@Override
	public Insurer saveInsurer(Insurer insurer) {
		// TODO Auto-generated method stub
		return ((CrudRepository<Insurer, Long>) insurerRepository).save(insurer);
	}

}
