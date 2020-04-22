package com.jairo.clientes.service;

import java.util.List;

import com.jairo.clientes.entity.Instructor;

public interface IInstructorService {
	public List<Instructor> listarInstructores();
	public Instructor agregarInstructor(Instructor instructor);
	public Instructor buscarPorId(Long id);
	//public Instructor actualizarInstructor(Long id, Instructor instructor);
	public void borrarInstructor(Long id);
}
