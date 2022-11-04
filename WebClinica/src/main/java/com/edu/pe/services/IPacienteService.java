package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Paciente;

public interface IPacienteService {

	public List<Paciente>ListarTodos();
	
	public int CantPacientePorDNI(String dni);
	
	public boolean Guardar(Paciente p);
	
	public Paciente ObtenerPorId(int idPaciente);
	
	public boolean Eliminar(int id);
	
	public List<Paciente> ListarPorTermino(String termino);
	
	public Paciente findPacienteByDNI(String dni);
	
	public List<Paciente> findPacienteByNombreLike(String term);
	
	public List<Paciente> findAllPacienteByDNI(String dni);
	
	
}
