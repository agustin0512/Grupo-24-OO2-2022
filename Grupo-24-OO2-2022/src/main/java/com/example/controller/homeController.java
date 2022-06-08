package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entities.User;
import com.example.service.implementation.UserService;



@Controller
public class homeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String inicio(Model model) { // Importamos Model para compartir informacion con la vista
		// Creamos los valores a compartir
		List<User> usuarios = userService.traer();
		// Mediante el metodo addAtribute de Model, enviamos los valores a compartir con la vista
		model.addAttribute("usuarioAgregar", new User());
		model.addAttribute("usuarios", usuarios);
		return "index"; // Indica el nombre de la vista (plantilla html)
	}
}
