package com.jairo.clientes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jairo.clientes.entity.Instructor;

@Repository
public interface IInstructorDao extends CrudRepository<Instructor, Long>{

}
