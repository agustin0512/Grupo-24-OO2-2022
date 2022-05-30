package com.example.controller;

import java.time.LocalDateTime;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserService;

@Controller
public class InicioCtrl {
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
	
	@PostMapping("/guardar")
	public String guardarUsuario(User userParam) {
		// Creamos el Usuario
		User user = new User();
		user.setNombre(userParam.getNombre());
		user.setApellido(userParam.getApellido());
		user.setMail(userParam.getMail());
		user.setTipodoc(userParam.getTipodoc());
		user.setDni(userParam.getDni());
		user.setUsername(userParam.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(userParam.getPassword()));
		user.setEnabled(true);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		// Creamos el Rol para el Usuario
		UserRole rol = new UserRole();
		rol.setRole("ROLE_USER");
		rol.setCreatedAt(LocalDateTime.now());
		rol.setUpdatedAt(LocalDateTime.now());
		rol.setUser(user);
		user.getUserRoles().add(rol);
		userService.guardar(user);
		return "redirect:/";
	}
}
