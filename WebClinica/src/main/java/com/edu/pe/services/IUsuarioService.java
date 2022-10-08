package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Usuario;

public interface IUsuarioService {

	public List<Usuario>ListarTodos();
	
	public Usuario BuscarPorNombreUsuario(String username, String pass);
	
	public Usuario BuscarPorNombreUsuario(String username);
	
	public boolean Guardar(Usuario user);
}
