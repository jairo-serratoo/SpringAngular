package com.jairo.clientes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> agregarInstructor(@RequestBody Instructor instructor) {
		
		Instructor instructorNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructorNew = instructorService.agregarInstructor(instructor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Instructor insertado con exito");
		response.put("instructor", instructorNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<?> buscarporId(@PathVariable Long id) {
		
		Instructor instructor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructor = instructorService.buscarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se puede acceder a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(instructor == null) {
			response.put("mensaje", "El instructor id: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Instructor>(instructor, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> actualizarInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
		
		Instructor instructorActual = instructorService.buscarPorId(id);
		Instructor instructorActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(instructorActual == null) {
			response.put("mensaje", "El isntructor id: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			instructorActual.setNombre(instructor.getNombre());
			instructorActual.setApellidoP(instructor.getApellidoP());
			instructorActual.setApellidoM(instructor.getApellidoM());
			instructorActual.setEmail(instructor.getEmail());
			instructorActual.setNumTel(instructor.getNumTel());
			instructorActual.setNumCel(instructor.getNumCel());
			instructorActualizado = instructorService.agregarInstructor(instructorActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Instructor actualizado con exito");
		response.put("instructor", instructorActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<?> borrarInstructor(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructorService.borrarInstructor(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al borrar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "instructor borrado con exito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
