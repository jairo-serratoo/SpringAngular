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

import com.jairo.clientes.entity.Cliente;
import com.jairo.clientes.service.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired 
	private IClienteService clienteService;
	
	@GetMapping(value = "/listar")
	public List<Cliente> listarClientes(){
		return clienteService.listarClientes();
	}
	
	@GetMapping(value = "/nombre/{id}")
	public String nombreCompletoById(@PathVariable Long id) {
		return clienteService.nombreCompletoById(id);
	}
	
	@GetMapping(value = "/listar/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id);
	}
	
	@PostMapping(value = "/agregar")
	public Cliente agregarCliente(@RequestBody Cliente cliente) {
		return clienteService.agregarCliente(cliente);
	}
	
	@PutMapping(value = "/actualizar/{id}")
	public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		return clienteService.actualizarCliente(id, cliente);
	}
	
	@DeleteMapping(value = "/borrar/{id}")
	public void borrarCliente(@PathVariable Long id) {
		 clienteService.borrarCliente(id);
	}
}
