package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.edu.pe.models.Hora;

@Repository
public interface HoraRepository extends JpaRepository<Hora, Integer> {
	
	@Query(value = "{call sp_horarios_disponibles(:idTurno , :idMedico , :fecha)}", nativeQuery = true)
	public List<Hora> ListadoHorariosDisp(@Param("idTurno") int idTurno, @Param("idMedico") int idMedico,
			@Param("fecha") String fecha);
}
