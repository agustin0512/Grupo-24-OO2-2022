package com.example.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.entities.Espacio;
import com.example.repo.IEspacioRepository;

@Service
public class EspacioService {
	
	private IEspacioRepository repo;
	
	public List<Espacio> traer() {
		return (List<Espacio>) repo.findAll();
	}
}
