package com.mwc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Category;

@Repository
@RepositoryRestResource(path="category", collectionResourceRel="category")
public interface CategoryRepository extends JpaRepository<Category, Long>, PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
	
	@Query("from Category where dbUser is not null and dbUser.id = :userId")
	List<Category> findAllUserSpecific(@Param("userId") long userId);
	
	Category findById(@Param("id") long id);
	
	Category findByIdAndName(@Param("id") long id, @Param("name") String name);
	
	@Query("from Category where name = :name and dbUser is not null and dbUser.id = :userId")
	Category findByUserIdAndName(@Param("userId") long userId, @Param("name") String name);
	
	@Query(" select categ " +
			" from Category as categ " +
			" left join categ.member as dbMember" +
			" where dbMember.dbUser.id = :userId")
	List<Category> findAllMemberSpecific(@Param("userId") long userId);
}
