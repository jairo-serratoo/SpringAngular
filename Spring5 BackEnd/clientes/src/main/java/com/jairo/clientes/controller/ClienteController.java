package com.jairo.clientes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		
		Cliente cliente = null;
		//Mapa para almacenar los mensajes de error o cachar las excepciones
		Map<String, Object> response = new HashMap<>();
		
		//Validaciones de acceso a la base de datos
		try {
			cliente = clienteService.buscarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Validacion de existencia de id
		if(cliente == null) {
			response.put("mensaje", "El cliente id: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping(value = "/agregar")
	public ResponseEntity<?> agregarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNew = clienteService.agregarCliente(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con exito");
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/actualizar/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		
		Cliente clienteNew = clienteService.buscarPorId(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(clienteNew == null) {
			response.put("mensaje", "El cliente id: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteNew.setNombre(cliente.getNombre());
			clienteNew.setApellidoP(cliente.getApellidoP());
			clienteNew.setApellidoM(cliente.getApellidoM());
			clienteNew.setEmail(cliente.getEmail());
			clienteNew.setNumTel(cliente.getNumTel());
			clienteNew.setNumCel(cliente.getNumCel());
			clienteActualizado = clienteService.agregarCliente(clienteNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente ha sido actualizado con exito");
		response.put("cliente", clienteActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteService.borrarCliente(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente borrado con exito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
