package com.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer>{

}
