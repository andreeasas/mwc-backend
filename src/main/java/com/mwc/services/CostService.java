package com.mwc.services;

import java.util.Date;
import java.util.List;

import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;

public interface CostService {

    public void saveOrUpdate(Cost cost);
    
    public void saveOrUpdate(Cost cost, long categId, String currencyCode);
    
    public void delete(Cost cost);
    
    public List<Cost> findCostsByUserInPeriod(User user, Date startDate, Date endDate);
    
    public List<Cost> findCostsByMemberInPeriod(Member member, Date startDate, Date endDate);
    
    public List<Cost> findCostsByMemberInPeriod(long memberId, Date startDate, Date endDate);
    
    public List<CategoryCostTotalDto> findByUsernameInPeriod(User user, Date startDate, Date endDate);
    
    public List<CategoryCostTotalDto> findWithCurrencyByUsernameInPeriod(User user, Date startDate, Date endDate);
    
}
