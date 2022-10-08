package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Turno;

public interface ITurnoService {

	public List<Turno> ListarTodos();
	
	public Turno BuscarPorId(int idTurno); 
}
