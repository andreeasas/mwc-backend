package com.mwc.queries;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.mwc.domain.Cost;
import com.mwc.domain.User;
import com.mwc.repositories.CostRepository;
import com.mwc.repositories.RoleRepository;
import com.mwc.repositories.UserRepository;
import com.mwc.services.CostService;
import com.mwc.services.CostServiceImpl;
import com.mwc.services.UserService;
import com.mwc.services.UserServiceImpl;

	@RunWith(SpringRunner.class)
	public class CostServiceImplTest {
		
		@TestConfiguration
	    static class CostServiceImplTestContextConfiguration {
	  
//	        @Bean
//	        public CostService costService() {
//	            return new CostServiceImpl();
//	        }
			
	        @Bean
	        public UserService userService() {
	            return new UserServiceImpl();
	        }
	    }
		
		@Autowired
		private UserService userService;
		
		@MockBean
	    private UserRepository userRepository;
		
		@MockBean
		private RoleRepository roleRepository;
		
		@MockBean
		private CostRepository costRepository;
		
		@MockBean
		private BCryptPasswordEncoder encoder;
		
		@Autowired
		private CostService costService;

	    @Before
	    public void setUp() throws Exception {
	    	User asa = new User("asa","asa","asa");
	    	 
	        Mockito.when(userRepository.findByUsername("asa"))
	          .thenReturn(asa);
	    }

	    @Test
	    public void testPersistence() {
	    	
	    	User asa = userService.findByUsername("asa");
	    	Assert.notNull(asa);
	    	System.out.println("user: "+asa);
	    	
//	    	Date startDate = new GregorianCalendar(2018, Calendar.JANUARY, 18).getTime();
//			Date endDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 20).getTime();
//			
//	    	costService.findByUsernameInPeriod(asa, startDate, endDate);
	    }
	}