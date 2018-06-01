package com.mwc.services;

import java.util.Date;
import java.util.List;

import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.ExpenseDateDto;
import com.mwc.dto.TotalStatisticsDto;

public interface CostService {

    public void saveOrUpdate(Cost cost);
    
    public void saveOrUpdate(Cost cost, long categId, String currencyCode);
    
    public void delete(Cost cost);
    
    public List<Cost> findCostsByUserInPeriod(User user, Date startDate, Date endDate);
    
    public ExpenseDateDto[] findExpensesForUserThisMonth(long userId, String currencyCode);
    
    public ExpenseDateDto[] findExpensesForMemberThisMonth(long memberId, String currencyCode);
    
    public List<Cost> findCostsByMemberInPeriod(Member member, Date startDate, Date endDate);
    
    public List<Cost> findCostsByMemberInPeriod(long memberId, Date startDate, Date endDate);
    
    public TotalStatisticsDto findTotalExpenseByUserInPeriod(User user, Date startDate, Date endDate);
    
    public TotalStatisticsDto getTotalCostsByUserAndMembersInPeriod(User user, Date startDate, Date endDate);
    
    public TotalStatisticsDto getTotalCostsByMemberInPeriod(Member member, Date startDate, Date endDate);
    
    public TotalStatisticsDto findTotalExpensesInPeriod(User user, Member member, Date startDate, Date endDate, String statisticsType); 
    
    public List<TotalStatisticsDto> findTotalExpensesWithCurrencyInPeriod(User user, Member member, Date startDate, Date endDate, String statisticsType);
    
}
