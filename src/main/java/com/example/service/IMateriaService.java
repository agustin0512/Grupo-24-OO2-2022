package com.example.service;

import java.util.List;

import com.example.entities.Materia;

public interface IMateriaService {
	public List<Materia> traer();
	public void guardar(Materia pedido);
	public Materia traer(int id);
}
