package com.jairo.clientes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructores")
public class Instructor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoP;

	@Column(name = "apellido_materno")
	private String apellidoM;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "numero_telefono")
	private Long numTel;

	@Column(name = "numero_celular")
	private Long numCel;

	public Instructor() {  }

	/**
	 * @param id
	 * @param nombre
	 * @param apellidoP
	 * @param apellidoM
	 * @param email
	 * @param numTel
	 * @param numCel
	 */
	public Instructor(Long id, String nombre, String apellidoP, String apellidoM, String email, Long numTel,
			Long numCel) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.email = email;
		this.numTel = numTel;
		this.numCel = numCel;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidoP
	 */
	public String getApellidoP() {
		return apellidoP;
	}

	/**
	 * @param apellidoP the apellidoP to set
	 */
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	/**
	 * @return the apellidoM
	 */
	public String getApellidoM() {
		return apellidoM;
	}

	/**
	 * @param apellidoM the apellidoM to set
	 */
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the numTel
	 */
	public Long getNumTel() {
		return numTel;
	}

	/**
	 * @param numTel the numTel to set
	 */
	public void setNumTel(Long numTel) {
		this.numTel = numTel;
	}

	/**
	 * @return the numCel
	 */
	public Long getNumCel() {
		return numCel;
	}

	/**
	 * @param numCel the numCel to set
	 */
	public void setNumCel(Long numCel) {
		this.numCel = numCel;
	}
	
	private static final long serialVersionUID = 1L;
}
