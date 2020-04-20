package com.jairo.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jairo.clientes.entity.Instructor;
import com.jairo.clientes.service.IInstructorService;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping(value = "/instructor")
public class InstructorController {

	@Autowired
	private IInstructorService instructorService;
	
	@GetMapping(value = "/listar")
	public List<Instructor> listarInstructores(){
		return instructorService.listarInstructores();
	}
	
	@PostMapping(value = "/agregar")
	public Instructor agregarInstructor(@RequestBody Instructor instructor) {
		return instructorService.agregarInstructor(instructor);
	}
	
	@GetMapping(value = "/listar/{id}")
	public Instructor buscarporId(@PathVariable Long id) {
		return instructorService.buscarPorId(id);
	}
	
	@PutMapping(value = "/update/{id}")
	public Instructor actualizarInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
		return instructorService.actualizarInstructor(id, instructor);
	}
	
	@DeleteMapping(value = "/borrar/{id}")
	public void borrarInstructor(@PathVariable Long id) {
		instructorService.borrarInstructor(id);
	}
}
