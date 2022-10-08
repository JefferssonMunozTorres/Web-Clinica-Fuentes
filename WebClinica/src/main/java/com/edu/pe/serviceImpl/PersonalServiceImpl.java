package com.edu.pe.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Personal;
import com.edu.pe.repository.PersonalRepository;
import com.edu.pe.services.IPersonalService;

@Service
public class PersonalServiceImpl implements IPersonalService {

	@Autowired
	private PersonalRepository repository;

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Personal> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public List<Personal> ListarPorPerfil(int idPerfil) {
		
		return repository.ListarPorPerfil(idPerfil);
	}

	@Override
	public boolean Guardar(Personal p) {
		try {
			repository.save(p);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Personal BuscarPorId(int id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	public boolean Eliminar(int id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public Personal BuscarPorUsuario(String username) {
		return repository.BuscarPorUsuario(username);
	}

	@Override
	public int CantidadPersonal(String nomPerfil) {
		return repository.CantidadPersonal(nomPerfil);
	}

	@Override
	public String GenerarUsuario(String perfil) {
		return repository.GenerarUsuario(perfil);
	}

	@Override
	public List<Personal> BuscarCriterios(String nombre,String dni,String perfil,String genero,String estado) {
		if ((nombre != null && !nombre.isEmpty()) || (dni != null && !dni.isEmpty())
				|| (perfil != null && !perfil.isEmpty()) || (genero != null && !genero.isEmpty())
				|| (estado != null && !estado.isEmpty())) {
			String sql = "select b from Personal b  where ";
			boolean isquery = false;
			if (nombre != null && !nombre.isEmpty()) {

				sql += " concat_ws(' ', b.nombre, b.apellido ) like  '%" + nombre + "%'";
				isquery = true;
			}
			if (dni != null && !dni.isEmpty()) {
				if (isquery) {
					sql += " and  b.dni ='" + dni + "'";
				} else {
					sql += " b.dni ='" + dni + "'";
				}
				isquery = true;
			}
			if (perfil != null && !perfil.isEmpty()) {
				if (isquery) {
					sql += " and  b.usuario.perfil.id =" + perfil + "";
				} else {
					sql += " b.usuario.perfil.id =" + perfil + "";
				}
				isquery = true;
			}
			if (genero != null && !genero.isEmpty()) {
				if (isquery) {
					sql += " and  b.genero ='" + genero + "'";
				} else {
					sql += " b.genero ='" + genero + "'";
				}
				isquery = true;
			}
			if (estado != null && !estado.isEmpty()) {
				if (isquery) {
					sql += " and  b.usuario.estado =" + estado + "";
				} else {
					sql += " b.usuario.estado =" + estado + "";
				}
				isquery = true;
			}
			TypedQuery<Personal> consulta = em.createQuery(sql, Personal.class);

			List<Personal> lista = consulta.getResultList();
			return lista;
		}
		return repository.findAll();
	}

}
