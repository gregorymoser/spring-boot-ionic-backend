package com.gm.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gm.conceptualmodel.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	
}