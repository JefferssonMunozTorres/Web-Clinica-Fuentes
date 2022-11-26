package com.edu.pe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import com.edu.pe.models.Perfil;
import com.edu.pe.models.Usuario;

@DataJpaTest
class UsuarioRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	void testLogin() {
		
		Perfil perfil1 = new Perfil();
		perfil1.setId_perfil(1); 		
		perfil1.setNombre_perfil("Administrador");
		
		Usuario usuario1 = new Usuario();
		usuario1.setId_usuario(1);
		usuario1.setPerfil(perfil1);
		usuario1.setUsername("Galvidio");
		usuario1.setPass("U$%FW#$");
		usuario1.setEstado(true);
		
		entityManager.merge(perfil1);
		entityManager.merge(usuario1);
		entityManager.flush();
		
		assertEquals(usuario1.getId_usuario(),
		usuarioRepository.BuscarPorNombreUsuario(usuario1.getUsername(), usuario1.getPass()).getId_usuario());
		
	}
	
	
	@Test
	void testCambioContrasena() {
		
		Perfil perfil2 = new Perfil();
		perfil2.setId_perfil(2); 		
		perfil2.setNombre_perfil("Tecnico");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId_usuario(2);
		usuario2.setPerfil(perfil2);
		usuario2.setUsername("Trimonetina");
		usuario2.setPass("SA3$A*P4s$");
		usuario2.setEstado(true);
		
		entityManager.merge(perfil2);
		entityManager.merge(usuario2);
		entityManager.flush();
		
		String nueva = "P4s$*SA3$A";
		
		Usuario user = usuarioRepository.BuscarPorNombreUsuario(usuario2.getUsername());
		user.setPass(nueva);
		
		assertNotNull(usuarioRepository.save(user));
		
		assertNotEquals(usuario2.getPass(),usuarioRepository.BuscarPorNombreUsuario(usuario2.getUsername()).getPass());
		
		
	}
}
