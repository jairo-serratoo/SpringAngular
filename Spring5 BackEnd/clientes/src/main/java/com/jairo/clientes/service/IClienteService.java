package com.jairo.clientes.service;

import java.util.List;

import com.jairo.clientes.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> listarClientes();
	public String nombreCompletoById(Long id);
	public Cliente buscarPorId(Long id);
	public Cliente agregarCliente(Cliente cliente);
	public Cliente actualizarCliente(Long id, Cliente cliente);
	public void borrarCliente(Long id);
	
}
