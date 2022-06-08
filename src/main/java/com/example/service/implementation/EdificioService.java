package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entities.Edificio;
import com.example.repo.IEdificioRepository;
import com.example.service.IEdificioService;

@Service
public class EdificioService implements IEdificioService {

	@Autowired
	@Qualifier("edificioRepository")
	private IEdificioRepository edificioRepository;
	
	
	@Override
	public List<Edificio> traer() {
		return (List<Edificio>) edificioRepository.findAll();
	}

}
