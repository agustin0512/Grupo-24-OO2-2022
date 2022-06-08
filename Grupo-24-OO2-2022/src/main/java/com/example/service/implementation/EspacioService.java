package com.example.service.implementation;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Aula;
import com.example.entities.Espacio;
import com.example.repo.IEspacioRepository;
import com.example.service.IEspacioService;
@Service
public class EspacioService implements IEspacioService {
	
	@Autowired
	private IEspacioRepository repo;
	
	@Autowired
	@Qualifier("aulaService")
	private AulaService aulaService;
	
	@Transactional( readOnly = true )
	public List<Espacio> traer() {
		return (List<Espacio>) repo.findAll();
	}
	
	@Override
	public Espacio traer(int id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Espacio guardar(Espacio espacio) {
		return repo.save(espacio);
	}

	@Override
	public boolean agregarTodosLosEspacios(LocalDate fechaInicio, LocalDate fechaFin) {
		List<Aula> lstAulas=aulaService.getAll();
		for(Aula aula : lstAulas){
			agregarEspaciosEntreFechas(fechaInicio,fechaFin,aula);
		}
		return true;
	}

	@Override
	public boolean agregarEspacioDia(LocalDate fecha, Aula aula) {
		guardar(new Espacio(fecha,'M',aula,true));
		guardar(new Espacio(fecha,'T',aula,true));
		guardar(new Espacio(fecha,'N',aula,true));
		return true;
	}

	@Override
	public boolean agregarEspaciosEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal, Aula aula) {
		// Cuatrimestre: definir fecha inicio y fin
		LocalDate actual = fechaInicial;
		while(actual.isBefore(fechaFinal.plusDays(1))) {
			agregarEspacioDia(actual,aula);
			actual=actual.plusDays(1);
		}
		return true;
	}
	

}