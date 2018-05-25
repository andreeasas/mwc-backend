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
	
//	@Query("from Category where member is not null")
//	List<Category> findAllMemberSpecific(@Param("userId") long userId);
}
