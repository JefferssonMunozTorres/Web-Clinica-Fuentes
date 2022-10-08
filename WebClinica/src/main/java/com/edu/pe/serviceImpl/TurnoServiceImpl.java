package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Turno;
import com.edu.pe.repository.TurnoRepository;
import com.edu.pe.services.ITurnoService;

@Service
public class TurnoServiceImpl implements ITurnoService{

	@Autowired
	private TurnoRepository repository;
	
	@Override
	public List<Turno> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public Turno BuscarPorId(int idTurno) {
		return repository.findById(idTurno).orElse(null);
	}

	
}
