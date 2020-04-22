package com.jairo.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairo.clientes.dao.IClienteDao;
import com.jairo.clientes.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public List<Cliente> listarClientes() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public String nombreCompletoById(Long id) {
		return clienteDao.juntarNombre(id);
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente agregarCliente(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	/*
	 * @Override public Cliente actualizarCliente(Long id, Cliente cliente) {
	 * cliente.setId(id); return clienteDao.save(cliente); }
	 */

	@Override
	public void borrarCliente(Long id) {
		clienteDao.deleteById(id);
	}

}
