package com.mwc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.views.CostPerCategoryView;

@Repository
@RepositoryRestResource()
public interface CostPerCategoryViewRepository extends JpaRepository<CostPerCategoryView, Long>, CrudRepository<CostPerCategoryView, Long>{
	
	@Query(	  value = "select totalPerCateg.CATEG_NAME, totalPerCateg.value, totalPerCateg.currency , round(value/total * 100, 2) as percent_From_Total" + 
			"  from COST_PER_CATEGORY_VIEW totalPerCateg " + 
			"  left join " + 
			"  (select sum(value) as total, currency " + 
			"  from COST_PER_CATEGORY_VIEW " + 
			"  group by currency) totalPerCurrency " + 
			"  on totalPerCateg.currency = totalPerCurrency.currency",
			  nativeQuery = true)
	List<Object[]> findTotalExpensesByCurrency();
	
	@Query(	  value = "select distinct totalPerCateg.currency, total" + 
			"  from COST_PER_CATEGORY_VIEW totalPerCateg " + 
			"  left join " + 
			"  (select sum(value) as total, currency " + 
			"  from COST_PER_CATEGORY_VIEW " + 
			"  group by currency) totalPerCurrency " + 
			"  on totalPerCateg.currency = totalPerCurrency.currency",
			  nativeQuery = true)
	List<Object[]> findTotalCurrencyPairs();
	
}
