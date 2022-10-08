package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hora")
public class Hora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_hora;
	
    @ManyToOne
    @JoinColumn(name = "id_Turno")
    private Turno turno;
    
	private String hora_inicio;
	private String hora_fin;
	
	@Override
	public String toString() {
		return "Hora [id_hora=" + id_hora + ", turno=" + turno + ", hora_inicio=" + hora_inicio + ", hora_fin="
				+ hora_fin + "]";
	}
	public int getId_hora() {
		return id_hora;
	}
	public void setId_hora(int id_hora) {
		this.id_hora = id_hora;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
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
