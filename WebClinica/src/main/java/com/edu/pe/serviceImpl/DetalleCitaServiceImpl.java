package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.DetalleCita;
import com.edu.pe.repository.DetalleCitaRepository;
import com.edu.pe.services.IDetalleCitaService;

@Service
public class DetalleCitaServiceImpl implements IDetalleCitaService{

	@Autowired
	private DetalleCitaRepository repository;
	
	@Override
	public List<DetalleCita> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public boolean Guardar(DetalleCita c) {
		try {
			repository.save(c);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public DetalleCita BuscarPorNro(int nro) {
		return repository.BuscarPorNroCita(nro);
	}

}
