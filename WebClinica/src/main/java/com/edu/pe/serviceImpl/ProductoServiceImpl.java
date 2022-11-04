package com.edu.pe.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edu.pe.models.Paciente;
import com.edu.pe.models.Producto;
import com.edu.pe.repository.ProductoRepository;
import com.edu.pe.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repository;

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Producto> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public boolean Guardar(Producto p) {
		try {
			repository.save(p);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Producto ObtenerPorId(int idProducto) {
		return repository.findById(idProducto).orElse(null);
	}

	@Override
	public boolean Eliminar(int id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Producto> ListarPorTermino(String termino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findProductoByNombreLike(String term) {
		return repository.findProductoByNombreLike("%" + term + "%");
	}

	@Override
	public List<Producto> BuscarCriterios(String nombre, String rangoMin, String rangoMax, String stockMin,
			String stockMax) {
		if ((nombre != null && !nombre.isEmpty()) || (rangoMin != null && !rangoMin.isEmpty())
				|| (rangoMax != null && !rangoMax.isEmpty()) || (stockMin != null && !stockMin.isEmpty())
				|| (stockMax != null && !stockMax.isEmpty())) {
			String sql = "select b from Producto b  where ";
			boolean isquery = false;
			if (nombre != null && !nombre.isEmpty()) {

				sql += " b.nombre like  '%" + nombre + "%'";
				isquery = true;
			}
			if (rangoMin != null && !rangoMin.isEmpty()) {
				if (isquery) {
					sql += " and  b.precio_unitario >=" + rangoMin + "";
				} else {
					sql += " b.precio_unitario >=" + rangoMin + "";
				}
				isquery = true;
			}
			if (rangoMax != null && !rangoMax.isEmpty()) {
				if (isquery) {
					sql += " and  b.precio_unitario <=" + rangoMax + "";
				} else {
					sql += " b.precio_unitario <=" + rangoMax + "";
				}
				isquery = true;
			}
			
			if (stockMin != null && !stockMin.isEmpty()) {
				if (isquery) {
					sql += " and  b.stock >=" + stockMin + "";
				} else {
					sql += " b.stock >=" + stockMin + "";
				}
				isquery = true;
			}
			if (stockMax != null && !stockMax.isEmpty()) {
				if (isquery) {
					sql += " and  b.stock <=" + stockMax + "";
				} else {
					sql += " b.stock <=" + stockMax + "";
				}
				isquery = true;
			}
			TypedQuery<Producto> consulta = em.createQuery(sql, Producto.class);

			List<Producto> lista = consulta.getResultList();
			return lista;
		}
		return repository.findAll();
	}

}
