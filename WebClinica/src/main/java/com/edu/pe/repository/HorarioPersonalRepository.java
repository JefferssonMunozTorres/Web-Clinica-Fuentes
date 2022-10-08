package com.edu.pe.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.HorarioPersonal;
import com.edu.pe.models.Personal;

@Repository
public interface HorarioPersonalRepository extends JpaRepository<HorarioPersonal, Integer> {

	@Query("SELECT h.personal FROM HorarioPersonal h WHERE h.turno.id_turno=?1 "
			+ " and h.personal.usuario.estado = true")
	public List<Personal> ListadoPersonalPorTurno(int idTurno);
	
	@Query("SELECT h FROM HorarioPersonal h WHERE h.personal.id_personal=?1 AND h.turno.id_turno=?2")
	public HorarioPersonal BuscarPorTurnoPersonal(int idPersonal , int idTurno);
	
    @Query(value = "{call sp_filtro_medicos_por_horario(?)}", nativeQuery = true)
    public List<Map<String,Object>> ListadoMedicosDispTurno(int idTurno);
}
