package com.wealthpark.purchasehistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wealthpark.purchasehistory.security.jpa.SecurityRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SecurityRepository.class)
public class PurchaseHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseHistoryApplication.class, args);
	}

}
