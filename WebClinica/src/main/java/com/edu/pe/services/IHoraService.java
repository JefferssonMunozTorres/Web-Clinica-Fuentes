package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Hora;

public interface IHoraService {

	public List<Hora> ListarTodos();
	
	public List<Hora> ListadoHorariosDisp(int idTurno,int idMedico,String fecha);
}
