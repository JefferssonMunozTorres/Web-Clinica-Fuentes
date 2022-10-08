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
@Table(name = "Horario_Personal")
public class HorarioPersonal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_horario;

	@ManyToOne
    @JoinColumn(name = "id_Turno")
    private Turno turno;
	
	@ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;
	
	@Override
	public String toString() {
		return "HorarioPersonal [id_horario=" + id_horario + ", turno=" + turno + ", personal=" + personal + "]";
	}

	public int getId_horario() {
		return id_horario;
	}

	public void setId_horario(int id_horario) {
		this.id_horario = id_horario;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
	
}
