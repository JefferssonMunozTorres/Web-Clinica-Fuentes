package com.edu.pe.services;

import java.util.List;

import com.edu.pe.models.Producto;

public interface ProductoService {
	
	public List<Producto>ListarTodos();
	
	public boolean Guardar(Producto p);
	
	public Producto ObtenerPorId(int idProducto);
	
	public boolean Eliminar(int id);
	
	public List<Producto> ListarPorTermino(String termino);
	
	public List<Producto> findProductoByNombreLike(String term);
	
	public List<Producto> BuscarCriterios(String nombre ,String rangoMin ,String rangoMax,String stockMin,String stockMax);
}
