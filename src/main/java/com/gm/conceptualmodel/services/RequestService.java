package com.gm.conceptualmodel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.conceptualmodel.domain.Request;
import com.gm.conceptualmodel.repositories.RequestRepository;
import com.gm.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;

	public Request find(Integer id) {
		Optional<Request> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Request.class.getName()));
	}
}