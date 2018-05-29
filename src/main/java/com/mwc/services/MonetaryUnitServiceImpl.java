package com.mwc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.MonetaryUnit;
import com.mwc.repositories.MonetaryUnitRepository;

@Service
public class MonetaryUnitServiceImpl implements MonetaryUnitService {

	@Autowired
	private MonetaryUnitRepository monetaryUnitRepository;
	
	@Override
	public MonetaryUnit getByCode(String code) {
		return monetaryUnitRepository.findByCode(code);
	}

}
