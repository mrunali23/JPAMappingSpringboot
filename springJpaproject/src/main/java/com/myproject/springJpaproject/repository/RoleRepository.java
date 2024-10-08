package com.myproject.springJpaproject.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.springJpaproject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	 Optional<Role> findByRoleName(String roleName);
}