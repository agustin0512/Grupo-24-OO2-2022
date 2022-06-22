package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import com.example.entities.Aula;

import com.example.repo.IAulaRepository;
import com.example.service.IAulaService;
@Service
public class AulaService implements IAulaService{
	@Autowired
	@Qualifier("aulaRepository")
	private IAulaRepository aulaRepository;
	

	@Override
	public
	List<Aula> getAulasByEdificio(int idEdificio){
		return aulaRepository.getAulasByEdificio(idEdificio);
	}
	
	

	
	@Override
	public List<Aula> getAll() {
		return (List<Aula>)aulaRepository.findAll();
	}

	

	@Override
	public Aula traer(int id) {
		return aulaRepository.getById(id);
	}

}
