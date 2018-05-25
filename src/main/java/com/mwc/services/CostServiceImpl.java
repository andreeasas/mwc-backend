package com.mwc.services;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.User;
import com.mwc.repositories.CostRepository;

public class CostServiceImpl implements CostService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CostRepository costRepository;
	
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
	public void findByUsernameAndCategoryInPeriod(User user, Category category, Date startDate, Date endDate) {
		String costCateg = //
				" from Cost as cost" + 
				" left join cost.category as categ" + 
				" left join cost.dbUser as dbUser" +
				" left join cost.um as um" +
				" where (dbUser = :user " +
				" where cost.costDate >= :startDate and cost.costDate <= :endDate)";
	 
		List<Cost> list2 = sessionFactory.getCurrentSession().createQuery(costCateg). //
			 	setParameter("user", user). //
				setParameter("startDate", startDate). //
				setParameter("endDate", endDate). //
				list();
		
		System.out.println("RESULT SIZE: " + list2.size());
		
	}

}
