package com.edu.pe.services;

import java.util.List;
import java.util.Map;

import com.edu.pe.models.HorarioPersonal;
import com.edu.pe.models.Personal;

public interface IHorarioPersonalService {

	public List<HorarioPersonal>ListarTodos();
	
	public List<Personal> ListadoPersonalPorTurno(int idTurno);
	
	public HorarioPersonal BuscarPorTurnoPersonal(int idPersonal , int idTurno);
	
	public boolean EliminarHorario(int idHorario);
	
	public List<Map<String, Object>> ListadoMedicosDispTurno(int idTurno);
	
	public boolean Guardar(HorarioPersonal objPers);
}
