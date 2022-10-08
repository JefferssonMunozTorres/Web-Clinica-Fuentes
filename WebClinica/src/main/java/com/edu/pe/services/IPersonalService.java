package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Personal;

public interface IPersonalService {

	public List<Personal> ListarTodos();
	
	public List<Personal>ListarPorPerfil(int idPerfil);
	
	public boolean Guardar(Personal p);
	
	public Personal BuscarPorId(int id);
	
	public boolean Eliminar(int id);
	
	public Personal BuscarPorUsuario(String username);
	
	public int CantidadPersonal(String nomPerfil);
	
	public String GenerarUsuario(String perfil);
	
	public List<Personal>BuscarCriterios(String nombre,String dni,String perfil,String genero,String estado);
}
