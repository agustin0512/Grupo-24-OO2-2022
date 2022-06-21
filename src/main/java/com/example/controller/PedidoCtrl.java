package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Materia;
import com.example.entities.NotaPedido;
import com.example.service.implementation.MateriaService;
import com.example.service.implementation.PedidoService;

@Controller
public class PedidoCtrl {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private MateriaService materiaService;
	
	/************* LISTAR PEDIDOS *************/
	@GetMapping("/pedidos/listar")
	public String listar(Model model) {
		List<NotaPedido> pedidos = pedidoService.traer();
		model.addAttribute("pedidos", pedidos);
		return "/views/pedidos/listar";
	}
	
	
	/************* AGREGAR PEDIDO *************/
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/pedidos/agregar")
	public String agregar(Model model) {
		List<Materia> materias = materiaService.traer();
		NotaPedido pedido= new NotaPedido();
		model.addAttribute("pedido", pedido);
		model.addAttribute("materias", materias);
		return "/views/pedidos/agregar";
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/pedidos/agregar")
	public String guardar(@ModelAttribute NotaPedido pedido) {
		pedidoService.guardar(pedido);
		System.out.println("Pedido guardado con exito!");
		return "redirect:/pedidos/listar";
		
	}
}
