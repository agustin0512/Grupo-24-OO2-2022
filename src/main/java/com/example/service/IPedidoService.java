package com.example.service;

import java.util.List;

import com.example.entities.NotaPedido;

public interface IPedidoService {
	public List<NotaPedido> traer();
	public void guardar(NotaPedido pedido);
	public NotaPedido traer(int id);
}
