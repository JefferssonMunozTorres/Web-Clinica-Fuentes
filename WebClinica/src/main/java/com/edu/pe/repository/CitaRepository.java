package com.edu.pe.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{

	@Query("select c from Cita c where c.fecha = ?1")
	public List<Cita> ListarPorFechas(Calendar fecha);
	
	@Query("select c from Cita c where c.fecha = ?1 and c.personal.id_personal=?2 and c.estado =?3 ")
	public List<Cita> ListarPorFechasYPersonal(Calendar fecha , int idPersonal, String estado);
	
	@Query("select c from Cita c where c.paciente.dni=?1 and  c.estado =?2 ")
	public List<Cita> ListarPorPaciente(String dni, String estado);
}
