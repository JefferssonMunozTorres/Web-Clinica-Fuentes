package com.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.DetalleCita;

@Repository
public interface DetalleCitaRepository  extends JpaRepository<DetalleCita, Integer>{

	@Query("select d from DetalleCita d where d.cita.nro_cita=?1")
	public DetalleCita BuscarPorNroCita(int nroCita);
}
