package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Query("select count(p) from Paciente p where p.dni = :dni")
	public int CantPacientePorDNI(String dni);
	
	@Query("select p from Paciente p where p.nombres like %:termino% or p.apellidos like %:termino%")
	public List<Paciente> ListarPorTermino(String termino);
	
	@Query("select p from Paciente p where p.dni = ?1")
	public Paciente findPacienteByDNI(String dni);
	
	@Query("select p from Paciente p where  concat_ws(' ', p.nombres, p.apellidos ) like  %?1% ")
	public List<Paciente> findPacienteByNombreLike(String term);
	
	@Query("select p from Paciente p where  p.dni = ?1 ")
	public List<Paciente> findAllPacienteByDNI(String dni);
}
