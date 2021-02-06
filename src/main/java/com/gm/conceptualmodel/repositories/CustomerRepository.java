package com.gm.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gm.conceptualmodel.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
}