package com.mwc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.User;

@Repository
@RepositoryRestResource(path="user", collectionResourceRel="user")
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
	User findByUsername(String username);
}
