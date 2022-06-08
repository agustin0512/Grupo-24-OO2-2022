package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.service.IEspacioService;


public class EspacioController {
	
	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;
	
	@GetMapping("/add")
	public ModelAndView agregarEspacios(@RequestParam(name="fechainicio", required=true, defaultValue="null") String sFechaInicio,
									@RequestParam(name="fechafin", required=true, defaultValue="null") String sFechaFin) {
		ModelAndView mAv = new ModelAndView("/espacios/crear");
		LocalDate fechaInicio = LocalDate.parse(sFechaInicio);
		LocalDate fechaFin = LocalDate.parse(sFechaFin);
		mAv.addObject("espaciosAgregados", espacioService.agregarTodosLosEspacios(fechaInicio, fechaFin));
		return mAv;
	}
}
