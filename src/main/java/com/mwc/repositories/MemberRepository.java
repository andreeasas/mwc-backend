package com.mwc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Member;

@Repository
@RepositoryRestResource(path="member", collectionResourceRel="member")
public interface MemberRepository extends JpaRepository<Member, Long>, PagingAndSortingRepository<Member, Long>, CrudRepository<Member, Long> {
	
	@Query("from Member where dbUser.id = :userId")
	List<Member> findAllByUserId(@Param("userId") long userId);
	
	Member findById(@Param("id") long id);
}
