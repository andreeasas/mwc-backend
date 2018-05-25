package com.mwc.queries;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mwc.domain.Cost;
import com.mwc.services.CostService;

	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringBootTest
	public class CostServiceTest {

//	    @Autowired
//	    private ProductRepository productRepository;
		
		@Autowired
		private CostService costService;

	    @Before
	    public void setUp() throws Exception {

	    }

	    @Test
	    public void testPersistence() {
	    	
	    	Date startDate = new GregorianCalendar(2018, Calendar.JANUARY, 18).getTime();
			Date endDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 20).getTime();
	    	
	    	costService.findByUsernameAndCategoryInPeriod(null, null, startDate, endDate);
	    }
	}