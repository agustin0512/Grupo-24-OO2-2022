package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.entities.Aula;
import com.example.entities.Espacio;

public interface IEspacioService {
	public Espacio traer(int id);
	public List<Espacio> traer();
	Espacio guardar(Espacio espacio);
	public boolean agregarEspacioDia(LocalDate fecha, Aula aula);
	public boolean agregarEspaciosEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal, Aula aula);
	public boolean agregarTodosLosEspacios(LocalDate fechaInicio, LocalDate fechaFin);
	
	
}
