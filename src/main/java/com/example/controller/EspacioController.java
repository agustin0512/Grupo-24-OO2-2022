package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entities.Aula;
import com.example.entities.Edificio;
import com.example.entities.Espacio;
import com.example.service.implementation.AulaService;
import com.example.service.implementation.EdificioService;
import com.example.service.implementation.EspacioService;



@Controller
public class EspacioController {
	
	@Autowired
	@Qualifier("espacioService")
	private EspacioService espacioService;
	@Autowired
	private EdificioService edificioService;
	@Autowired
	private AulaService aulaService;
	
	
	/************* LISTAR ESPACIOS *************/
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AUDIT')")
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
			@RequestParam(name="fechafin", required=true, defaultValue="null") String sFechaFinal, RedirectAttributes redirectAttrs){
		// Creamos el Espacio
		LocalDate fechaInicio = LocalDate.parse(sFechaInicial);
		LocalDate fechaFin = LocalDate.parse(sFechaFinal);
		try {
			espacioService.agregarTodosLosEspacios(fechaInicio, fechaFin);
			redirectAttrs.addFlashAttribute("ok", "Los espacios fueron agregados");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error", "Ya existen espacios dentro del rango de fechas ingresado");
			e.printStackTrace();
		}
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
	public String guardarEspacioDia(@ModelAttribute("espacio") Espacio espacio, RedirectAttributes redirectAttrs){
		// Creamos el Espacio
		try {
			espacioService.crear(espacio.getFecha(), espacio.getTurno(), espacio.getAula(), true);
			redirectAttrs.addFlashAttribute("ok", "El espacio fue agregado");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error", "El espacio ya existe");
			e.printStackTrace();
		}
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
			@RequestParam(name="anio", required=true) String anio,
			RedirectAttributes redirectAttrs) {
		// Creamos el Espacio
		try {
			espacioService.agregarEspacioMes(Integer.parseInt(mes), Integer.parseInt(anio), espacio.getTurno(), espacio.getAula());
			redirectAttrs.addFlashAttribute("ok", "Los espacios fueron agregados");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error", "Ya existen espacios dentro del rango de fechas ingresado");
			e.printStackTrace();
		}
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
