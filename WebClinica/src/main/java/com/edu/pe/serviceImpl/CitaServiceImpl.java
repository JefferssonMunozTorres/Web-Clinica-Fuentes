package com.edu.pe.serviceImpl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Cita;
import com.edu.pe.repository.CitaRepository;
import com.edu.pe.services.ICitaService;

@Service
public class CitaServiceImpl implements ICitaService {

	@Autowired
	private CitaRepository repository;

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Cita> ListarTodos() {
		return repository.findAll();
	}

	@Override
	public boolean Guardar(Cita c) {
		try {
			repository.save(c);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Cita> ListarPorFechas(Calendar fecha) {
		return repository.ListarPorFechas(fecha);
	}

	@Override
	public Cita BuscarPorId(int nro) {
		return repository.findById(nro).orElse(null);
	}

	@Override
	public boolean Eliminar(int nro) {
		try {
			repository.deleteById(nro);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Cita> ListarPorFechasYPersonal(Calendar fecha, int idPersonal, String estado) {
		return repository.ListarPorFechasYPersonal(fecha, idPersonal,estado);
	}

	@Override
	public List<Cita> ListarPorPaciente(String dni, String estado) {
		return repository.ListarPorPaciente(dni, estado);
	}

	@Override
	public List<Cita> BuscarCriterios(String nombre, String medico, String fechaInicio, String fechaFin,String estado) {
		if ((nombre != null && !nombre.isEmpty()) || (fechaInicio != null && !fechaInicio.isEmpty())
				|| (fechaFin != null && !fechaFin.isEmpty()) || (medico != null && !medico.isEmpty())
				|| (estado != null && !estado.isEmpty())) {
			
			String sql = "select b from Cita b  where ";
			boolean isquery = false;
			if (nombre != null && !nombre.isEmpty()) {

				sql += " concat_ws(' ', b.paciente.nombres, b.paciente.apellidos ) like  '%" + nombre + "%'";
				isquery = true;
			}
			if (medico != null && !medico.isEmpty()) {
				if (isquery) {
					sql += " and concat_ws(' ', b.personal.nombre, b.personal.apellido ) like  '%" + medico + "%'";
				}else {
					sql += " concat_ws(' ', b.personal.nombre, b.personal.apellido ) like  '%" + medico + "%'";
				}
				
				isquery = true;
			}
			if (fechaInicio != null && !fechaInicio.isEmpty()) {
				if (isquery) {
					sql += " and  b.fecha >='" + fechaInicio + "'";
				} else {
					sql += " b.fecha >='" + fechaInicio + "'";
				}
				isquery = true;
			}
			if (fechaFin != null && !fechaFin.isEmpty()) {
				if (isquery) {
					sql += " and  b.fecha <='" + fechaFin + "'";
				} else {
					sql += " b.fecha <='" + fechaFin + "'";
				}
				isquery = true;
			}
			if (estado != null && !estado.isEmpty()) {
				if (isquery) {
					sql += " and  b.estado ='" + estado + "'";
				}else {
					sql += " b.estado ='" + estado + "'";
				}
				
				isquery = true;
			}
			TypedQuery<Cita> consulta = em.createQuery(sql, Cita.class);

			List<Cita> lista = consulta.getResultList();
			return lista;
		}
		return repository.findAll();
	}
}
