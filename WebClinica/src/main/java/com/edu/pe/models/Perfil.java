package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Perfil")
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private int id_perfil;
	private String nombre_perfil;
	
	@Override
	public String toString() {
		return "Perfil [id_perfil=" + id_perfil + ", nombre_perfil=" + nombre_perfil + "]";
	}
	public int getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}
	public String getNombre_perfil() {
		return nombre_perfil;
	}
	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}

}
