package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.NotaPedido;
import com.example.repo.IPedidoRepository;
import com.example.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService{
	
	@Autowired
	private IPedidoRepository repo;

	@Override
	@Transactional( readOnly = true )
	public List<NotaPedido> traer() {
		return  (List<NotaPedido>) repo.findAll();
	}

	@Override
	@Transactional
	public void guardar(NotaPedido pedido) {
		repo.save(pedido);
		
	}

	@Override
	@Transactional( readOnly = true )
	public NotaPedido traer(int id) {
		return repo.findById(id).orElse(null);
	}

}
