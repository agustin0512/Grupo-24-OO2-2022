package com.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Aula;
import com.example.entities.Edificio;
import com.example.entities.Espacio;
import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.AulaService;
import com.example.service.implementation.EdificioService;
import com.example.service.implementation.EspacioService;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;

@Controller
public class InicioCtrl {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private EspacioService espacioService;
	@Autowired
	private EdificioService edificioService;
	@Autowired
	private AulaService aulaService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	/************* INICIO *************/
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AUDIT')")
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
		
		return "/views/usuarios/agregar";
		} // Indicamos la plantilla html a usar (Form Agregar)

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/usuarios/agregar")
	public String guardarUsuario(@ModelAttribute User user) {
		user.setEnabled(true);
		String password=encoder.encode(user.getPassword());
		user.setPassword(password);
		
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
		return "/views/usuarios/modificar"; // Indicamos la plantilla html a usar (Form Modificar)
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
	
	/************* CREAR ESPACIOS *************/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/espacios/agregar")
	public String crear(Model model) {
		model.addAttribute("espacio", new Espacio());	// Instanciamos un Espacio para cargar en el Form
		return "/views/espacios/crear"; // Indicamos la plantilla html a usar (Form Agregar)
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/espacios/agregar")
	public String guardarEspacio(@RequestParam(name="fechainicio", required=true, defaultValue="null") String sFechaInicial,
			@RequestParam(name="fechafin", required=true, defaultValue="null") String sFechaFinal) {
		// Creamos el Espacio
		LocalDate fechaInicio = LocalDate.parse(sFechaInicial);
		LocalDate fechaFin = LocalDate.parse(sFechaFinal);
		espacioService.agregarTodosLosEspacios(fechaInicio, fechaFin);
		// Redireccion a Inicio
		return "redirect:/espacios/listar";
	}
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/espacios/agregarEspacioDia")
	public String crearEspacioDia(Model model) {
		List<Aula> aulas = aulaService.getAll();
		List<Edificio> edificios = edificioService.traer();
		model.addAttribute("espacio", new Espacio());	// Instanciamos un Espacio para cargar en el Form
		model.addAttribute("edificios", edificios);
		model.addAttribute("aulas", aulas);
		return "/views/espacios/crearEspacioDia"; // Indicamos la plantilla html a usar (Form Agregar)
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/espacios/agregarEspacioDia")
	public String guardarEspacioDia(@ModelAttribute("espacio") Espacio espacio) {
		espacio.setLibre(true);
		// Creamos el Espacio
		espacioService.guardar(espacio);
		// Redireccion a Inicio
		return "redirect:/espacios/listar";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/espacios/agregarEspacioMes")
	public String crearEspacioMes(Model model) {
		List<Aula> aulas = aulaService.getAll();
		List<Edificio> edificios = edificioService.traer();
		model.addAttribute("espacio", new Espacio());	// Instanciamos un Espacio para cargar en el Form
		model.addAttribute("edificios", edificios);
		model.addAttribute("aulas", aulas);
		return "/views/espacios/crearEspacioMes"; // Indicamos la plantilla html a usar (Form Agregar)
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/espacios/agregarEspacioMes")
	public String guardarEspacioMes(@ModelAttribute("espacio") Espacio espacio, 
			@RequestParam(name="mes", required=true) String mes,
			@RequestParam(name="anio", required=true) String anio) {
		// Creamos el Espacio
		espacioService.agregarEspacioMes(Integer.parseInt(mes), Integer.parseInt(anio), espacio.getTurno(), espacio.getAula());
		// Redireccion a Inicio
		return "redirect:/espacios/listar";
	}
	
	
	/************* ESPACIO LIBRE/OCUPADO *************/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/espacios/ocupado/{id}")
	public String ocupado(Espacio espacio) {
		espacio = espacioService.traer(espacio.getId());
		espacio.setLibre(!espacio.isLibre());
		espacioService.guardar(espacio);
		return "redirect:/espacios/listar"; // Redirecciona a Inicio
	}
}
