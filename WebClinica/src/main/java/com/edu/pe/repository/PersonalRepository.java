package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	@Query("select p from Personal p where p.usuario.perfil.id_perfil = ?1")
	public List<Personal>ListarPorPerfil(int idPerfil);
	
	@Query("select p from Personal p where p.usuario.username=?1")
	public Personal BuscarPorUsuario(String username);
	
	@Query("select count(p) from Personal p where p.usuario.perfil.nombre_perfil=?1 and p.usuario.estado=true")
	public int CantidadPersonal(String nomPerfil);
	
	@Query(value = "{call sp_generar_usuario(?1)}" ,nativeQuery = true)
	public String GenerarUsuario(String perfil);
	
	@Query("select p from Personal p where p.nombre = ?1")
	public List<Personal>ListarPorNombre(String nombre);
}
