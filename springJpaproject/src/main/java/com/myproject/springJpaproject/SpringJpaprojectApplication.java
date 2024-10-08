package com.myproject.springJpaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringJpaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaprojectApplication.class, args);
	}

}
