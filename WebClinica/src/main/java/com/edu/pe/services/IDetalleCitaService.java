package com.edu.pe.services;

import java.util.List;


import com.edu.pe.models.DetalleCita;

public interface IDetalleCitaService {

	public List<DetalleCita>ListarTodos();
	
	public boolean Guardar(DetalleCita c);
	
	public DetalleCita BuscarPorNro(int nro);
}
