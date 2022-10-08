package com.edu.pe.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.HorarioPersonal;
import com.edu.pe.models.Personal;
import com.edu.pe.repository.HorarioPersonalRepository;
import com.edu.pe.services.IHorarioPersonalService;

@Service
public class HorarioPersonalServiceImpl implements IHorarioPersonalService {

	@Autowired
	private HorarioPersonalRepository repository;

	@Override
	public List<HorarioPersonal> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public List<Personal> ListadoPersonalPorTurno(int idTurno) {
		return repository.ListadoPersonalPorTurno(idTurno);
	}

	@Override
	public HorarioPersonal BuscarPorTurnoPersonal(int idPersonal, int idTurno) {
		return repository.BuscarPorTurnoPersonal(idPersonal, idTurno);
	}

	@Override
	public boolean EliminarHorario(int idHorario) {
		try {
			repository.deleteById(idHorario);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> ListadoMedicosDispTurno(int idTurno) {
		return repository.ListadoMedicosDispTurno(idTurno);
	}

	@Override
	public boolean Guardar(HorarioPersonal objPers) {
		try {
			repository.save(objPers);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
