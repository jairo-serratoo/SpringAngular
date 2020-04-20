package com.jairo.clientes.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jairo.clientes.entity.Cliente;

@Repository
public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
	//Aqui podria poner las querys personalizadas
	@Query(value = "SELECT CONCAT(c.NOMBRE, c.APELLIDO_PATERNO, c.APELLIDO_MATERNO) FROM CLIENTE c WHERE ID = ?1", nativeQuery = true)
	public String juntarNombre(Long id);
}
