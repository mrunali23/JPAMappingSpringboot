package com.myproject.springJpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.springJpaproject.entity.Right;

@Repository
public interface RightRepository extends JpaRepository<Right, Long> {
}
