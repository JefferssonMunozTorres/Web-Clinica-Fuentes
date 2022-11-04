package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findProductoByNombreLike(String term);
}
