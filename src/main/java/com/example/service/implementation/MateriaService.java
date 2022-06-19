package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Materia;
import com.example.repo.IMateriaRepository;
import com.example.service.IMateriaService;

@Service
public class MateriaService implements IMateriaService{
	
	@Autowired
	private IMateriaRepository repo;

	@Override
	@Transactional( readOnly = true )
	public List<Materia> traer() {
		return (List<Materia>) repo.findAll();
	}

	@Override
	@Transactional
	public void guardar(Materia materia) {
		repo.save(materia);
		
	}

	@Override
	@Transactional( readOnly = true )
	public Materia traer(int id) {
		return repo.findById(id).orElse(null);
	}
}
