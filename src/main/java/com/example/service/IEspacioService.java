package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.entities.Aula;
import com.example.entities.Espacio;

public interface IEspacioService {
	public Espacio traer(int id);
	public List<Espacio> traer();
	Espacio guardar(Espacio espacio) throws Exception;
	public boolean agregarEspaciosEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal, Aula aula) throws Exception;
	public boolean agregarTodosLosEspacios(LocalDate fechaInicio, LocalDate fechaFin) throws Exception;
	public Espacio traer(LocalDate fecha, char turno, Aula aula);
	
}
