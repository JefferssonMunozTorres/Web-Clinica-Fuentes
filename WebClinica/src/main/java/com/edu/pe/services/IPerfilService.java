package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Perfil;

public interface IPerfilService {

	public List<Perfil>ListarTodos();
	
	public Perfil ObtenerPorId(int id);
}
