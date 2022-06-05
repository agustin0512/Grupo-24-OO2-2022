package com.example.controller;

import java.time.LocalDateTime;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		model.addAttribute("usuario", new User());	// Instanciamos un User para cargar en el Form
		return "/views/usuarios/formAgregar"; // Indicamos la plantilla html a usar (Form Agregar)
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/usuarios/agregar")
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
	
		// Insertamos User y Roles en la BD
		userService.guardar(user);
		userRoleService.guardar(rol);
		// Redireccion a Inicio
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
