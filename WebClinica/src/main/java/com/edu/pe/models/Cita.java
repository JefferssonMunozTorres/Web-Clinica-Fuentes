package com.edu.pe.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Cita")
public class Cita implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nro_cita;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @OneToOne
    @JoinColumn(name = "id_hora")
    private Hora hora;
    
    @OneToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Calendar fecha;
    
    private String estado;
    
	@Override
	public String toString() {
		return "Cita [nro_cita=" + nro_cita + ", paciente=" + paciente + ", hora=" + hora + ", personal=" + personal
				+ ", fecha=" + fecha + ", estado=" + estado + "]";
	}

	public int getNro_cita() {
		return nro_cita;
	}

	public void setNro_cita(int nro_cita) {
		this.nro_cita = nro_cita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
}
