package com.mwc.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.domain.views.CostPerCategoryView;
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.dto.ExpenseDateDto;
import com.mwc.dto.TotalStatisticsDto;
import com.mwc.repositories.CategoryRepository;
import com.mwc.repositories.CostPerCategoryViewRepository;
import com.mwc.repositories.CostRepository;
import com.mwc.repositories.MonetaryUnitRepository;

@Service
public class CostServiceImpl implements CostService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CostRepository costRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private MonetaryUnitRepository monetaryUnitRepository;
	
	@Autowired
	private CostPerCategoryViewRepository costCategViewRepository;

	@Override
	public void saveOrUpdate(Cost cost) {
		costRepository.save(cost);
	}
	
	@Override
	public void saveOrUpdate(Cost cost, long categId, String currencyCode) {
		Category costCateg = categoryRepository.findById(categId); // how connect to category ??
		cost.setCategory(costCateg);
		cost.setDbUser(costCateg.getDbUser());
		cost.setMember(costCateg.getMember());
		
		cost.setUm(monetaryUnitRepository.findByCode(currencyCode));
		
		costRepository.save(cost);
	}

	@Override
	public void delete(Cost cost) {
		costRepository.delete(cost);
	}

	@Override
	public List<Cost> findCostsByUserInPeriod(User user, Date startDate, Date endDate) {
		return costRepository.getCostsByUserInPeriod(user, startDate, endDate);
	}

	@Override
	public List<Cost> findCostsByMemberInPeriod(Member member, Date startDate, Date endDate) {
		return costRepository.getCostsByMemberInPeriod(member, startDate, endDate);
	}

	@Override
	public List<Cost> findCostsByMemberInPeriod(long memberId, Date startDate, Date endDate) {
		return costRepository.getCostsByMemberInPeriod(memberId, startDate, endDate);
	}
	
	@Override
	public TotalStatisticsDto findTotalExpenseByUserInPeriod(User user, Date startDate, Date endDate) {
		List<Object[]> totalCostsByUser = costRepository.getTotalCostsByUserInPeriod(user, startDate, endDate);
		
		return createTotalStatisticsDto(totalCostsByUser);
	}
	
	@Override
	public TotalStatisticsDto getTotalCostsByUserAndMembersInPeriod(User user, Date startDate, Date endDate) {
		List<Object[]> totalCostsByUserAndMembers = costRepository.getTotalCostsByUserAndMembersInPeriod(user, startDate, endDate);
		
		return createTotalStatisticsDto(totalCostsByUserAndMembers);
	}
	
	@Override
	public TotalStatisticsDto getTotalCostsByMemberInPeriod(Member member, Date startDate, Date endDate) {
		List<Object[]> totalCostsByMember = costRepository.getTotalCostsByMemberInPeriod(member, startDate, endDate);
		
		return createTotalStatisticsDto(totalCostsByMember);
	}

	@Override
	public List<TotalStatisticsDto> findTotalExpensesWithCurrencyInPeriod(User user, Member member, Date startDate, Date endDate, String statisticsType) {
		
		createDataInCostCategoryView(user, member, startDate, endDate, statisticsType);
		
		// find sum of expenses, percent from total - grouped on category and currency
		List<Object[]> totalExpensesByCurrency = costCategViewRepository.findTotalExpensesByCurrency();
		
		List<CategoryCostTotalDto> costDtos = new ArrayList<CategoryCostTotalDto>();
		for (Object[] array : totalExpensesByCurrency) {
			CategoryCostTotalDto costDto = new CategoryCostTotalDto();
			costDto.setCategName((String) array[0]);
			costDto.setValue(((BigDecimal) array[1]).doubleValue());
			costDto.setCurrency((String) array[2]);
			costDto.setPercentFromTotal(((BigDecimal) array[3]).doubleValue());
			
			costDtos.add(costDto);
		}
		
		// group results by currency
		List<Object[]> totalCurrencyPairs = costCategViewRepository.findTotalCurrencyPairs();
		
		List<TotalStatisticsDto> statisticsDtos = new ArrayList<>();
		for (Object[] array : totalCurrencyPairs) {
			String currency = (String) array[0];
			double total = ((BigDecimal) array[1]).doubleValue();
			List<CategoryCostTotalDto> expensesDtos = costDtos.stream().filter(costDto -> costDto.getCurrency().equalsIgnoreCase(currency)).collect(Collectors.toList());			
			
			statisticsDtos.add(new TotalStatisticsDto(expensesDtos, total, currency));
		}
		
		return statisticsDtos;
	}

	/**
	 * insert data in auxiliary table
	 * */
	private void createDataInCostCategoryView(User user, Member member, Date startDate, Date endDate,
			String statisticsType) {
		
		String queryText = //
		" select categ.name as name, sum(cost.value) as val, um.code as currency" +
		" from Cost as cost" + 
		" inner join cost.category as categ" +
		" left join cost.member as dbMember" +
		" inner join cost.um as um" +
		" where "+
		addWhereClause(statisticsType) +
		" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
		" group by (categ.name, um.code)";
		
		Query query = em.createQuery(queryText);
		addParamAccordingStatistics(query, statisticsType, user, member, startDate, endDate);
		
		@SuppressWarnings("unchecked")
		List<Object[]> totalCostsInPeriod = query.getResultList();
		
		List<CostPerCategoryView> costCategViews = new ArrayList<>();
		
		for (Object[] array : totalCostsInPeriod) {
			CostPerCategoryView costCategView = new CostPerCategoryView((String) array[0], (String) array[2]);
			costCategView.setValue((Double) array[1]);
			costCategViews.add(costCategView);
		}
		
		costCategViewRepository.deleteAll();
		costCategViewRepository.save(costCategViews);
	}

	@Override
	public TotalStatisticsDto findTotalExpensesInPeriod(User user, Member member, Date startDate, Date endDate,
			String statisticsType) {

		String queryText = //
		" select categ.name as name, sum(cost.value) as val" +
		" from Cost as cost" + 
		" left join cost.category as categ" +
		" left join cost.member as dbMember" +
		" where "+
		addWhereClause(statisticsType) +
		" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
		" group by (categ.name)";
		
		Query query = em.createQuery(queryText);
		addParamAccordingStatistics(query, statisticsType, user, member, startDate, endDate);
		
		@SuppressWarnings("unchecked")
		List<Object[]> totalCostsInPeriod = query.getResultList();
		
		return createTotalStatisticsDto(totalCostsInPeriod);
	}

	private String addWhereClause(String statisticsType) {
		switch (statisticsType) {
		case "User":
			return " cost.dbUser = :user ";
		case "User and Members":
			return " (cost.dbUser = :user " + 
					" or dbMember.dbUser = :user) ";
		case "Member":
			return " cost.member = :member ";
		}
		return "";
	}
	
	private void addParamAccordingStatistics(Query query, String statisticsType, User user, Member member, Date startDate, Date endDate) {
		if (statisticsType.equals("User") || statisticsType.equals("User and Members")) {
			query.setParameter("user", user);
		}
		if (statisticsType.equals("Member")) {
			query.setParameter("member", member);
		}
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}
	
	private TotalStatisticsDto createTotalStatisticsDto(List<Object[]> totalCostsByUserInPeriod) {
		List<CategoryCostTotalDto> costsDto = new ArrayList<CategoryCostTotalDto>();
		Double total = Double.valueOf(0);
		
		for (Object[] array : totalCostsByUserInPeriod) {
			Double totalCateg = (Double) array[1];
			total = Double.sum(total, totalCateg);
			
			CategoryCostTotalDto costDto = new CategoryCostTotalDto();
			costDto.setCategName((String) array[0]);
			costDto.setValue(totalCateg.doubleValue());
			costsDto.add(costDto);
		}
		
		final Double totalSum = total;
		
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		costsDto.forEach(costDto -> {
			double percentFromTotal = costDto.getValue()*100/totalSum.doubleValue();
			costDto.setPercentFromTotal(Double.parseDouble(decimalFormat.format(percentFromTotal)));
		});
		
		return new TotalStatisticsDto(costsDto, total.doubleValue());
	}

	@Override
	public ExpenseDateDto[] findExpensesByUserThisMonth(long userId, String currencyCode) {
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = calendar.getTime();
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = calendar.getTime();
		
		List<Object[]> costsThisMonth = costRepository.getEverydayCostsByUserInPeriod(userId, firstDayOfMonth, lastDayOfMonth, currencyCode);
		
		List<ExpenseDateDto> expenseDateDtos = new ArrayList<>();
		costsThisMonth.forEach(cost -> {
			ExpenseDateDto expenseDateDto = new ExpenseDateDto();
			expenseDateDto.setDateMili(((Date) cost[0]).getTime());
			expenseDateDto.setExpenseValue((double) cost[1]);
			expenseDateDtos.add(expenseDateDto);
		});
		
		return expenseDateDtos.toArray(new ExpenseDateDto[expenseDateDtos.size()]);
	}

}

