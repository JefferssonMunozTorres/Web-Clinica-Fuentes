package com.edu.pe.services;

import java.util.Calendar;
import java.util.List;

import com.edu.pe.models.Cita;

public interface ICitaService {

	public List<Cita>ListarTodos();
	
	public boolean Guardar(Cita c);
	
	public List<Cita> ListarPorFechas(Calendar fecha);
	
	public List<Cita> ListarPorFechasYPersonal(Calendar fecha , int idPersonal, String estado);
	
	public List<Cita> ListarPorPaciente(String dni, String estado);
	
	public Cita BuscarPorId(int nro);
	
	public boolean Eliminar(int nro);
	
	public List<Cita> BuscarCriterios(String nombre ,String medico ,String  fechaInicio,String fechaFin,String estado);
}
