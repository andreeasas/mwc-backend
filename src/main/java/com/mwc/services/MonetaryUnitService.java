package com.mwc.services;

import java.util.List;

import com.mwc.domain.MonetaryUnit;

public interface MonetaryUnitService {

	public MonetaryUnit getByCode(String code);
	
	public List<String> findAllCurrenciesCodes();
	
}
