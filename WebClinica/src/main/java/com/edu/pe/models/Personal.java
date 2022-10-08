package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Personal")
public class Personal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_personal")
	private int id_personal;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private String nombre;
	private String apellido;
	private String dni;
	private String genero;
	private String correo;

	@Override
	public String toString() {
		return "Personal [id_personal=" + id_personal + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido="
				+ apellido + ", dni=" + dni + ", genero=" + genero + ", correo=" + correo + "]";
	}
	
	public String NombresCompletos() {
		return nombre+" "+apellido;
	}

	public int getId_personal() {
		return id_personal;
	}

	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
