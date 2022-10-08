package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Perfil;
import com.edu.pe.repository.PerfilRepository;
import com.edu.pe.services.IPerfilService;

@Service
public class PerfilServiceImpl implements IPerfilService{

	@Autowired
	private PerfilRepository repository;
	
	@Override
	public List<Perfil> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public Perfil ObtenerPorId(int id) {
		
		return repository.findById(id).orElse(null);
	}

}
