package com.mwc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.MonetaryUnit;

@Repository
@RepositoryRestResource(path="monetaryUnit", collectionResourceRel="monetaryUnit")
public interface MonetaryUnitRepository extends PagingAndSortingRepository<MonetaryUnit, Long>, CrudRepository<MonetaryUnit, Long> {

	MonetaryUnit findByCode(@Param("code") String code);
	
}
