package com.mwc.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.dto.TotalStatisticsDto;
import com.mwc.repositories.CategoryRepository;
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
	public List<TotalStatisticsDto> findTotalExpenseWithCurrencyByUserInPeriod(User user, Date startDate, Date endDate) {
		
//		List<Object[]> totalCostsByUserInPeriod = costRepository.getTotalCostsByUserInPeriod(user, startDate, endDate);
//		
//		List<CategoryCostTotalDto> costDtos = new ArrayList<CategoryCostTotalDto>();
//		
//		for (Object[] array : totalCostsByUserInPeriod) {
//			CategoryCostTotalDto costDto = new CategoryCostTotalDto();
//			costDto.setCategName((String) array[0]);
//			costDto.setValue((Double) array[1]);
//			costDto.setCurrency((String) array[2]);
//			costDtos.add(costDto);
//		}
//		
//		costDtos.stream().filter(costDto -> costDto.getCurrency().equals("EUR")).collect(Collectors.toList());
		
		String queryText = " select categ.name as name, sum(cost.value) as val, um.code as currency" +
		" from Cost as cost" + 
		" inner join cost.category as categ" +
		" inner join cost.um as um" +
		" where cost.dbUser = :user " +
		" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
		" group by (categ.name)";
		
		Query query = em.createQuery(queryText);
		query.setParameter("user", user);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		List<Object[]> totalCostsByUserInPeriod = query.getResultList();

		return null;
	}
	
//	" select categ.name as name, sum(cost.value) as val" +
//	" from Cost as cost" + 
//	" left join cost.category as categ" +
//	" left join cost.member as dbMember" +
//	" where "+
//	" (cost.dbUser = :user " +
//	" or cost.member = :member " +
//	" or dbMember.dbUser = :user) "
//	" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
//	" group by (categ.name)"

}

