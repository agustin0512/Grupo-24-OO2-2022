package com.example.controller;

import java.time.LocalDateTime;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.entities.Espacio;
import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.EspacioService;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;


//@RequestMapping("/views/usuarios")
@Controller
public class InicioCtrl {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private EspacioService espacioService;

	/************* INICIO *************/
	@GetMapping("/usuarios/listar")
	public String inicio(Model model) { // Importamos Model para compartir informacion con la vista
		// Creamos los valores a compartir
		List<User> usuarios = userService.traer();
		
		model.addAttribute("titulo", "Listado de Usuarios");

		// Mediante el metodo addAtribute de Model, enviamos los valores a compartir con la vista
		model.addAttribute("usuarios", usuarios);
		return "/views/usuarios/listar"; // Indicamos la plantilla html a usar(index)
	}
	
	/************* AGREGAR USUARIO *************/
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarios/agregar")
	public String agregar(Model model) {
		List<UserRole> roles=userRoleService.traer();
		User user= new User();
		model.addAttribute("titulo","Formulario creacion de usuario");
		model.addAttribute("usuario", user);// Instanciamos un User para cargar en el Form
		model.addAttribute("roles", roles);
		
		return "/views/usuarios/formAgregar";
		} // Indicamos la plantilla html a usar (Form Agregar)

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/usuarios/agregar")
	public String guardarUsuario(@ModelAttribute User user) {
		System.out.println("ID             "+user.getRol().getIdRol());
		System.out.println("ROL               "+user.getRol().getRole());
		userService.guardar(user);									
		System.out.println("Cliente guardado con exito!");
		return "redirect:/usuarios/listar";
		
	}
	
	/************* MODIFICAR USUARIO *************/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarios/modificar/{id}")
	public String modificar(User user, Model model) {
		user = userService.traer(user.getId());	// Se obtiene el User a Modificar
		model.addAttribute("usuario", user);	// Se comparte el User para el autocompletado del form
		return "/views/usuarios/formModificar"; // Indicamos la plantilla html a usar (Form Modificar)
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/usuarios/modificar")
	public String modificarUsuario(User user) {
		user.setUpdatedAt(LocalDateTime.now());	// Solo se actualiza Fecha Update
		userService.guardar(user);				// El resto de atributos se obtienen desde el form
		return "redirect:/usuarios/listar";
	}
	
	/************* DESACTIVAR USUARIO *************/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarios/desactivar/{id}")
	public String desactivar(User user) {
		user = userService.traer(user.getId());
		user.setEnabled(!user.isEnabled());		// Solo se modifica el estado del User (enabled)
		userService.guardar(user);
		return "redirect:/usuarios/listar"; // Redirecciona a Inicio
	}
	
	/************* LISTAR ESPACIOS *************/
	@GetMapping("/espacios/listar")
	public String listarEspacios(Model model) { // Importamos Model para compartir informacion con la vista
		// Creamos los valores a compartir
		List<Espacio> espacios = espacioService.traer();
		model.addAttribute("titulo", "Espacios");
	
		// Mediante el metodo addAtribute de Model, enviamos los valores a compartir con la vista
		model.addAttribute("espacios", espacios);
		return "/views/espacios/listar"; // Indicamos la plantilla html a usar(index)
	}
}
