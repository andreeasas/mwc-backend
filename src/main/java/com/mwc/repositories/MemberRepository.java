package com.mwc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Member;
import com.mwc.domain.User;

@Repository
@RepositoryRestResource(path="member", collectionResourceRel="member")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, CrudRepository<Member, Long> {

	List<Member> findByDbUser(@Param("dbUser") User dbUser);
}
