package com.edu.pe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.pe.models.Usuario;
import com.edu.pe.repository.UsuarioRepository;
import com.edu.pe.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public List<Usuario> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public Usuario BuscarPorNombreUsuario(String username, String pass) {
		return repository.BuscarPorNombreUsuario(username,pass);
	}
	
	@Override
	public boolean Guardar(Usuario u) {
		try {
			repository.save(u);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Usuario BuscarPorNombreUsuario(String username) {
		return repository.BuscarPorNombreUsuario(username);
	}

}
