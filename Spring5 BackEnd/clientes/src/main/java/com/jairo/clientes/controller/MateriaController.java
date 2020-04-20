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
	public Materia agregarMateria(@RequestBody Materia materia) {
		return materiaService.agregarMateria(materia);
	}
	
	@GetMapping("listar/{id}")
	public Materia listarPorId(@PathVariable Long id) {
		return materiaService.listarPorId(id);
	}
	
	@PutMapping("/actualizar/{id}")
	public Materia actualizarMateria(@PathVariable Long id, @RequestBody Materia materia) {
		return materiaService.actaulizarMateria(id, materia);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrarmateria(@PathVariable Long id) {
		materiaService.borrarMateria(id);
	}
}
