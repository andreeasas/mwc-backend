package com.mwc.services;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.User;
import com.mwc.dto.CategoryCostTotalDto;
import com.mwc.repositories.CostRepository;

@Service
public class CostServiceImpl implements CostService {
	
	private CostRepository costRepository;
	
	@Autowired
	public CostServiceImpl(CostRepository costRepository) {
		this.costRepository = costRepository;
	}

	@Override
	public void save(Cost cost) {
		costRepository.save(cost);
	}

	@Override
	public void delete(Cost cost) {
		costRepository.delete(cost);
	}

	@Override
	public void update(Cost cost) {
		costRepository.delete(cost);
	}

	@Override
	public List<CategoryCostTotalDto> findByUsernameInPeriod(User user, Date startDate, Date endDate) {
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
	public List<CategoryCostTotalDto> findWithCurrencyByUsernameInPeriod(User user, Date startDate, Date endDate) {
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
