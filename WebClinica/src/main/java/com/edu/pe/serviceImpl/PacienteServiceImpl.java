package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Paciente;
import com.edu.pe.repository.PacienteRepository;
import com.edu.pe.services.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private PacienteRepository repository;
	
	@Override
	public List<Paciente> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public int CantPacientePorDNI(String dni) {
		return repository.CantPacientePorDNI(dni);
	}

	@Override
	public boolean Guardar(Paciente p) {
		try {
			repository.save(p);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Paciente ObtenerPorId(int idPaciente) {
		
		return repository.findById(idPaciente).orElse(null);
	}

	@Override
	public boolean Eliminar(int id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public List<Paciente> ListarPorTermino(String termino){
		return repository.ListarPorTermino(termino);
	}

	@Override
	public Paciente findPacienteByDNI(String dni) {
		return repository.findPacienteByDNI(dni);
	}

	@Override
	public List<Paciente> findPacienteByNombreLike(String term) {
		return repository.findPacienteByNombreLike("%" + term + "%");
	}

	@Override
	public List<Paciente> findAllPacienteByDNI(String dni) {
		return repository.findAllPacienteByDNI(dni);
	}

}
