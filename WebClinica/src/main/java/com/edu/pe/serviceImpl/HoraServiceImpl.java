package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Hora;
import com.edu.pe.repository.HoraRepository;
import com.edu.pe.services.IHoraService;

@Service
public class HoraServiceImpl implements IHoraService{

	@Autowired
	private HoraRepository repository;
	
	@Override
	public List<Hora> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public List<Hora> ListadoHorariosDisp(int idTurno, int idMedico, String fecha) {
		return repository.ListadoHorariosDisp(idTurno, idMedico, fecha);
	}

}
