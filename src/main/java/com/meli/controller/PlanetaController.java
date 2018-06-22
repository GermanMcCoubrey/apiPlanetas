package com.meli.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.models.PeriodoModel;
import com.meli.services.PeriodoService;


@RestController
public class PlanetaController {

	private final PeriodoService service;

	public PlanetaController(PeriodoService service) {
		this.service = service;
	}

	// Punto 1 del bonus
	@RequestMapping(value = "/iniciar", method = RequestMethod.POST)
	public ResponseEntity iniciar() {
		service.iniciarCarga();
		return new ResponseEntity(HttpStatus.OK);
	}

	// Punto 1 / 2 / 3
	@RequestMapping(value = "/periodo/{id}", method = RequestMethod.GET)
	public List<PeriodoModel> getPeriodos(@PathVariable("id") String periodo) {
		System.out.println("Me llego el periodo " + periodo);
		return service.getPorPeriodos(periodo);
	}

	// Punto 2
	@RequestMapping(value = "/maximo", method = RequestMethod.GET)
	public PeriodoModel getMaximo() {
		return service.getMaximo();
	}

	@RequestMapping(value = "/clima/{id}", method = RequestMethod.GET)
	public PeriodoModel getPeriodoPorDia(@PathVariable("id") long dia) {
		System.out.println("Me llego el dia " + dia);
		return service.getPeriodoPorDia(dia);
	}

}
