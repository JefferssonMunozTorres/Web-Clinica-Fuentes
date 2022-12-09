package com.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.pe.models.Cita;
import com.edu.pe.models.DetalleCita;
import com.edu.pe.services.ICitaService;
import com.edu.pe.services.IDetalleCitaService;

@Controller
@RequestMapping("/historia")
public class HistoriaController {

	@Autowired
	private IDetalleCitaService detalleCitaService;
	
	@Autowired
	private ICitaService citaService;
	
	@GetMapping("/")
	public String Inicio() {
		return "HistoriaPaciente";
	}
	
	@GetMapping("/listarCitasPaciente")
	public String ListarMisCitas(Model model ,String dni) {

		List<Cita> lista = citaService.ListarPorPaciente(dni, "Atendido");
		model.addAttribute("citas" , lista);
		
		return "listado/ListadoHistoriaPaciente";
	}
	
	@GetMapping("/buscarDetalleCita")
	public ResponseEntity<?>BuscarDetalle(@RequestParam int nro){
		DetalleCita d = detalleCitaService.BuscarPorNro(nro);
		return ResponseEntity.ok(d);

	}
}
