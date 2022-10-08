package com.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u where u.username = ?1 and u.pass=?2")
	public Usuario BuscarPorNombreUsuario(String username , String pass);

	@Query("SELECT u FROM Usuario u where u.username = ?1")
	public Usuario BuscarPorNombreUsuario(String username);
}
