package com.mwc.services;

import java.util.Date;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.User;

public interface CostService {

    public void save(Cost cost);
    
    public void delete(Cost cost);
    
    public void update(Cost cost);
    
    public void findByUsernameAndCategoryInPeriod(User user, Category category, Date startDate, Date endDate);
	
}
