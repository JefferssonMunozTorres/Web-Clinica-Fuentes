package com.edu.pe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import com.edu.pe.models.Producto;

@DataJpaTest
class ProductoRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Test
	void testResgistrarProducto() {
		
		Producto producto1 = new Producto();
		producto1.setId_producto(1);
		producto1.setNombre("Enjuague Bucal");
		producto1.setPrecio_unitario(2.5);
		producto1.setStock(25);
		
		entityManager.merge(producto1);
		entityManager.flush();
		
		assertNotNull(productoRepository.save(producto1));
		
	}
	
	
	@Test
	void testListarProductos() {
		
		Producto producto2 = new Producto();
		producto2.setId_producto(2);
		producto2.setNombre("Cepillo");
		producto2.setPrecio_unitario(4.5);
		producto2.setStock(25);
		
		Producto producto3 = new Producto();
		producto3.setId_producto(3);
		producto3.setNombre("Hilo Dental");
		producto3.setPrecio_unitario(21.5);
		producto3.setStock(20);
		
		Producto producto4 = new Producto();
		producto4.setId_producto(4);
		producto4.setNombre("Pasta Dental");
		producto4.setPrecio_unitario(12.5);
		producto4.setStock(14);
		
		entityManager.merge(producto2);
		entityManager.merge(producto3);
		entityManager.merge(producto4);
		entityManager.flush();
		
		List<Producto> listaEnviada1 = new ArrayList<Producto>();
		listaEnviada1.add(producto2);
		
		
		List<Producto> listaEnviada2 = new ArrayList<Producto>();
		listaEnviada2.add(producto4);
		
		assertEquals(listaEnviada1.get(0).getNombre(),productoRepository.findProductoByNombreLike("Ce").get(0).getNombre());
		
		assertEquals(listaEnviada2.get(0).getNombre(),productoRepository.findProductoByNombreLike("Pas").get(0).getNombre());
		
	}
}
