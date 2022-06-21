package com.example.service;

import java.util.List;

import com.example.entities.Aula;

public interface IAulaService {
	public List<Aula> getAll();
	Aula traer(int id);
}
