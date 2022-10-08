package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Turno")
public class Turno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turno") 
	private int id_turno;
	
	private String nombre;
	private String hora_inicio;
	private String hora_fin;
	
	@Override
	public String toString() {
		return "Turno [id_turno=" + id_turno + ", nombre=" + nombre + ", hora_inicio=" + hora_inicio + ", hora_fin="
				+ hora_fin + "]";
	}
	public int getId_turno() {
		return id_turno;
	}
	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}
}
