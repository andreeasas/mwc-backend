package com.mwc.utils.datastage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mwc.domain.Cost;
import com.mwc.domain.User;

public class CostServiceTest {
	
	public static void main(String[] args) {
		
		WithSessionAndTransaction<Object> createView = new WithSessionAndTransaction<Object>() {
			
			 @Override
		      protected void executeBusinessLogic(Session session) {
				
				Date startDate = new GregorianCalendar(2018, Calendar.JANUARY, 18).getTime();
				Date endDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 20).getTime();
				 
				Query findUser = session.createQuery("from User where username = 'asa'");
				User user = (User) findUser.uniqueResult();
				 
				System.out.println("USER: "+user+" "+user.getPassword() + " "+user.getId());
				 
//				costInPeriodSqlTest(session, startDate, endDate, user);
					
//				costInPeriodHqlQuery(session, startDate, endDate);
			 }

			private void costInPeriodHqlQuery(Session session, Date startDate, Date endDate) {
				String costCateg = //
						" from Cost as cost" + 
						" left join cost.category as categ" + 
						" left join cost.dbUser as dbUser" +
						" left join cost.um as um" +
//							" where (cost.dbUser = :user " +
						" where cost.costDate >= :startDate and cost.costDate <= :endDate)";
 
				List<Cost> list2 = session.createQuery(costCateg). //
//						 	setParameter("user", user). //
						setParameter("startDate", startDate). //
						setParameter("endDate", endDate). //
						list();
				
				System.out.println("RESULT SIZE: " + list2.size());
			}

			private void costInPeriodSqlTest(Session session, Date startDate, Date endDate, User user) {
				String costCategInPeriod = //
							" select categ.NAME as categ_name, sum(cost.VALUE) as val, um.code as monetary_unit" + 
							"  from cost" + 
							"  left join category categ" + 
							"  on cost.CATEGORY = categ.ID" + 
							"  left join APP_USER appUser" + 
							"  on cost.DB_USER = appUser.ID" + 
							"  left join MONETARY_UNIT um" + 
							"  on cost.UM = um.CODE" + 
							"  where (appUser.ID = :userId" + 
							"  and cost.COST_DATE >= :startDate and cost.COST_DATE <= :endDate)" + 
							"  group by (categ.name, um.code)";
					
					List<Object> list = session.createSQLQuery(costCategInPeriod). //
							setParameter("userId", user.getId()). //
							setParameter("startDate", startDate). //
							setParameter("endDate", endDate). //
							list();
					
					System.out.println("RESULT SIZE: " + list.size());
			}
		};
		
		createView.run();
		
	}

	 
}
