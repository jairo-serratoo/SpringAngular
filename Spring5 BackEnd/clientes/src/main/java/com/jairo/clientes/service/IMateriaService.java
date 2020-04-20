package com.jairo.clientes.service;

import java.util.List;

import com.jairo.clientes.entity.Materia;

public interface IMateriaService {
	public List<Materia> listarMaterias();
	public Materia agregarMateria(Materia materia);
	public Materia listarPorId(Long id);
	public Materia actaulizarMateria(Long id, Materia materia);
	public void borrarMateria(Long id);
}
