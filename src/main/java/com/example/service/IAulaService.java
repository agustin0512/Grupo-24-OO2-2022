package com.example.service;

import java.util.List;



import com.example.entities.Aula;

public interface IAulaService {
	public List<Aula> getAll();
	List<Aula> getAulasByEdificio(int idEdificio);
	Aula traer(int id);
}
