package com.myproject.springJpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.springJpaproject.entity.Module;

@Repository

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
