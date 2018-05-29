package com.mwc.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.repositories.CategoryRepository;
import com.mwc.repositories.CostRepository;
import com.mwc.repositories.MonetaryUnitRepository;

@Service
public class CostServiceImpl implements CostService {
	
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
	public List<CategoryCostTotalDto> findTotalExpenseByUserInPeriod(User user, Date startDate, Date endDate) {
		List<Object[]> totalCostsByUserInPeriod = costRepository.getTotalCostsByUserInPeriod(user, startDate, endDate);
		
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
		
		System.out.println("TOTAL: " + total.doubleValue());
		
		final Double totalSum = total;
		
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		costsDto.forEach(costDto -> {
			double percentFromTotal = costDto.getValue()*100/totalSum.doubleValue();
			costDto.setPercentFromTotal(Double.parseDouble(decimalFormat.format(percentFromTotal)));
		});
		
		costsDto.forEach(costDto -> {
			System.out.println(costDto.getCategName() + " " + costDto.getValue()+ " " + costDto.getPercentFromTotal());
		}); //
		
		return costsDto;
	}

	@Override
	public List<CategoryCostTotalDto> findTotalExpenseWithCurrencyByUserInPeriod(User user, Date startDate, Date endDate) {
List<Object[]> totalCostsByUserInPeriod = costRepository.getTotalCostsByUserInPeriod(user, startDate, endDate);
		
		List<CategoryCostTotalDto> costsDto = new ArrayList<CategoryCostTotalDto>();
		Double total = Double.valueOf(0);
		
		for (Object[] array : totalCostsByUserInPeriod) {
			Double totalCateg = (Double) array[1];
			total = Double.sum(total, totalCateg);
			
			CategoryCostTotalDto costDto = new CategoryCostTotalDto();
			costDto.setCategName((String) array[0]);
			costDto.setValue(totalCateg.doubleValue());
			costDto.setCurrency((String) array[2]);
			costsDto.add(costDto);
		}
		
		System.out.println("TOTAL: " + total.doubleValue());
		
		
		
		return null;
	}

}
