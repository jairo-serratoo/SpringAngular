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

import com.jairo.clientes.entity.Materia;
import com.jairo.clientes.service.IMateriaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/materias")
public class MateriaController {
	
	@Autowired
	private IMateriaService materiaService;
	
	@GetMapping("/listar")
	public List<Materia> listarMateria(){
		return materiaService.listarMaterias();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregarMateria(@RequestBody Materia materia) {
		
		Materia materiaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			materiaNew = materiaService.agregarMateria(materia);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Materia creada con exito");
		response.put("materia", materiaNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("listar/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Long id) {
		
		Materia materia = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			materia = materiaService.listarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se puede acceder a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(materia == null) {
			response.put("mensaje", "El id: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Materia>(materia, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarMateria(@PathVariable Long id, @RequestBody Materia materia) {
		
		Materia materiaActual = materiaService.listarPorId(id);
		Materia materiaActualizada = null;
		Map<String, Object> response = new HashMap<>();
		
		if(materiaActual == null) {
			response.put("mensaje", "El id: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			materiaActual.setNombre(materia.getNombre());
			materiaActual.setDescripcion(materia.getDescripcion());
			materiaActualizada = materiaService.agregarMateria(materiaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Materia actualizada con exito");
		response.put("materia", materiaActualizada);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> borrarmateria(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			materiaService.borrarMateria(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Materia borrada con exito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
