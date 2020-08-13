package com.test.gruposalinas.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EdadController {

	
	@GetMapping("/edad/{edad}")
	public String mayoriaEdad(@PathVariable long edad) {
		if(edad>=18) {
			return "Si tienes " +edad + " ya eres mayor de edad!";
		}
		
		return "Aun no cumples la mayoria de edad";
	}
}
