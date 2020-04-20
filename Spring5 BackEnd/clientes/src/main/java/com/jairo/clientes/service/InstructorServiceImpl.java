package com.jairo.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairo.clientes.dao.IInstructorDao;
import com.jairo.clientes.entity.Instructor;

@Service
public class InstructorServiceImpl implements IInstructorService {

	@Autowired 
	private IInstructorDao instructorDao;
	
	@Override
	public List<Instructor> listarInstructores() {
		return (List<Instructor>) instructorDao.findAll();
	}

	@Override
	public Instructor agregarInstructor(Instructor instructor) {
		return instructorDao.save(instructor);
	}

	@Override
	public Instructor buscarPorId(Long id) {
		return instructorDao.findById(id).orElse(null);
	}

	@Override
	public Instructor actualizarInstructor(Long id, Instructor instructor) {
		instructor.setId(id);
		return instructorDao.save(instructor);
	}

	@Override
	public void borrarInstructor(Long id) {
		instructorDao.deleteById(id);
	}

}
