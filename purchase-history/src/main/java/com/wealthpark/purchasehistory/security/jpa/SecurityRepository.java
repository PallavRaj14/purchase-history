package com.wealthpark.purchasehistory.security.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wealthpark.purchasehistory.schema.Purchaser;

public interface SecurityRepository extends JpaRepository<Purchaser, Integer>{
	
	Optional<Purchaser> findByUserName(String userName);
}
