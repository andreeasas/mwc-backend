package com.mwc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Cost;


@Repository
@RepositoryRestResource(path="cost", collectionResourceRel="cost")
public interface CostRepository extends PagingAndSortingRepository<Cost, Long>, CrudRepository<Cost, Long> {
}
