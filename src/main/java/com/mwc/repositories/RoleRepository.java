package com.mwc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mwc.domain.Role;

@Repository
@RepositoryRestResource(path="role", collectionResourceRel="role")
public interface RoleRepository extends JpaRepository<Role, Long>{
}
