package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_paciente;
	
	private String nombres;
	private String apellidos;
	private String dni;
	private String genero;
	private String celular;
	private String direccion;
	private String correo;
	
	@Override
	public String toString() {
		return "Paciente [id_paciente=" + id_paciente + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni="
				+ dni + ", genero=" + genero + ", celular=" + celular + ", direccion=" + direccion + ", correo="
				+ correo + "]";
	}
	
	public String nombresCompletos() {
		return nombres+" "+apellidos;
	}
	
	public int getId_paciente() {
		return id_paciente;
	}
	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
