package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Espacio;
import com.example.service.IEspacioService;

@Controller
public class EspacioController {
	
	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;
	
	@GetMapping("/add")
	public ModelAndView agregarEspacios(@RequestParam(name="fechainicio", required=true, defaultValue="null") String sFechaInicio,
									@RequestParam(name="fechafin", required=true, defaultValue="null") String sFechaFin) {
		ModelAndView mAv = new ModelAndView("/espacios/crear");
		mAv.addObject("espaciosAgregados", espacioService.agregarTodosLosEspacios(LocalDate.parse(sFechaInicio), LocalDate.parse(sFechaFin)));
		return mAv;
	}
	
	
}
