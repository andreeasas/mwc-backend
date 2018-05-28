package com.mwc.services;

import java.util.Date;
import java.util.List;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;

public interface CostService {

    public void save(Cost cost);
    
    public void delete(Cost cost);
    
    public void update(Cost cost);
    
    public List<CategoryCostTotalDto> findByUsernameInPeriod(User user, Date startDate, Date endDate);
    
    public List<CategoryCostTotalDto> findWithCurrencyByUsernameInPeriod(User user, Date startDate, Date endDate);
	
}
