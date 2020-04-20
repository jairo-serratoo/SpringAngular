package com.jairo.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairo.clientes.dao.IMateriaDao;
import com.jairo.clientes.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService{

	@Autowired 
	private IMateriaDao materiaDao;
	
	@Override
	public List<Materia> listarMaterias() {
		return (List<Materia>) materiaDao.findAll();
	}

	@Override
	public Materia agregarMateria(Materia materia) {
		return materiaDao.save(materia);
	}

	@Override
	public Materia listarPorId(Long id) {
		return materiaDao.findById(id).orElse(null);
	}

	@Override
	public Materia actaulizarMateria(Long id, Materia materia) {
		materia.setId(id);
		return materiaDao.save(materia);
	}

	@Override
	public void borrarMateria(Long id) {
		materiaDao.deleteById(id);
	}

}
