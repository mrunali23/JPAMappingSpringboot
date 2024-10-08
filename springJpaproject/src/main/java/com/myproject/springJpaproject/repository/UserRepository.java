package com.myproject.springJpaproject.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.springJpaproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByRoles_RoleName(String roleName);
}