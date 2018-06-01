package com.mwc.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.User;


@Repository
@RepositoryRestResource(path="cost", collectionResourceRel="cost")
public interface CostRepository extends PagingAndSortingRepository<Cost, Long>, CrudRepository<Cost, Long> {
	
	@Query(" from Cost as cost" + 
			" where cost.dbUser = :user " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate")
    public List<Cost> getCostsByUserInPeriod(@Param("user") User user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" from Cost as cost" + 
			" where cost.member = :member " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate")
    public List<Cost> getCostsByMemberInPeriod(@Param("member") Member member, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" select cost " +
			" from Cost as cost" + 
			" left join cost.member as dbMember " +
			" where dbMember.id = :memberId " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate")
    public List<Cost> getCostsByMemberInPeriod(@Param("memberId") long memberId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" from Cost as cost" + 
			" where cost.dbUser.id = :userId " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" and cost.um.code = :currency")
    public List<Cost> getCostsByUserInPeriod(@Param("userId") long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("currency") String currencyCode);

	@Query( " select cost.costDate as day, sum(cost.value) as expenseInDay" +
			" from Cost as cost " + 
			" where cost.dbUser.id = :userId " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" and cost.um.code = :currency" +
			" group by (cost.costDate)" +
			" order by (cost.costDate)")
    public List<Object[]> getDailyCostsForUserInPeriod(@Param("userId") long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("currency") String currencyCode);
	
	@Query( " select cost.costDate as day, sum(cost.value) as expenseInDay" +
			" from Cost as cost " +
			" left join cost.member as dbMember" +
			" where dbMember.id = :memberId " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" and cost.um.code = :currency" +
			" group by (cost.costDate)" +
			" order by (cost.costDate)")
    public List<Object[]> getDailyCostsForMemberInPeriod(@Param("memberId") long memberId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("currency") String currencyCode);
    
	@Query(" select categ.name as name, sum(cost.value) as val" +
			" from Cost as cost" + 
			" left join cost.category as categ" +
			" where cost.dbUser = :user " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" group by (categ.name)")
	public List<Object[]> getTotalCostsByUserInPeriod(@Param("user") User user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" select categ.name as name, sum(cost.value) as val" +
			" from Cost as cost" + 
			" left join cost.category as categ" +
			" left join cost.member as dbMember" +
			" where " +
			" (cost.dbUser = :user " +
			" or dbMember.dbUser = :user) " +
			" and (cost.costDate >= :startDate and cost.costDate <= :endDate)" +
			" group by (categ.name)")
	public List<Object[]> getTotalCostsByUserAndMembersInPeriod(@Param("user") User user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" select categ.name as name, sum(cost.value) as val" +
			" from Cost as cost" + 
			" left join cost.category as categ" +
			" where cost.member = :member " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" group by (categ.name)")
	public List<Object[]> getTotalCostsByMemberInPeriod(@Param("member") Member member, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(" select categ.name as name, sum(cost.value) as val, um.code as currency" +
			" from Cost as cost" + 
			" inner join cost.category as categ" +
			" inner join cost.um as um" +
			" where cost.dbUser = :user " +
			" and cost.costDate >= :startDate and cost.costDate <= :endDate" +
			" group by (categ.name)")
	public List<Object[]> getTotalCostsAndCurrencyByUserInPeriod(@Param("user") User user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
