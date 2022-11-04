package com.edu.pe.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.edu.pe.models.Paciente;
import com.edu.pe.services.IPacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;
	
	@GetMapping("/")
	public String Inicio() {
		return "MantPaciente";
	}
	
	@GetMapping("/listar")
	public String ListarTodos(Model model) {
		List<Paciente> lista = pacienteService.ListarTodos();
		
		model.addAttribute("pacientes" , lista);
		
		return "listado/ListadoPacientes";
	}
	
	@PostMapping("/guardar")
	@ResponseBody
	public String Guardar(Paciente p) {
		String mensj = "";
		boolean res;
		
		int cantDNI =pacienteService.CantPacientePorDNI(p.getDni());
		
		if(cantDNI > 0) {
			mensj = "El DNI "+p.getDni()+" ya se encuentra registrado";
		}else {
			res = pacienteService.Guardar(p);
			
			if(res) {
				mensj = "OK";
			}else {
				mensj = "No se ha podido registrar paciente";
			}
		}

		return mensj;
	}
	
	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> Eliminar(@PathVariable("id") int id){
		Paciente e = pacienteService.ObtenerPorId(id);
		boolean res;
		
		if(e !=null) {
			res = pacienteService.Eliminar(id);
			
			return ResponseEntity.ok(res);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping(path = "buscar/{id}")
	@ResponseBody
	public Paciente BuscarPaciente(@PathVariable("id") int id) {
		return pacienteService.ObtenerPorId(id);
	}
	
	@GetMapping("/listarTodos")
	public ResponseEntity<?>ListarPacintes(){
		List<Paciente> lista = pacienteService.ListarTodos();
		
		return ResponseEntity.ok(lista);
	}
	

	@GetMapping("/filtro")
	public ResponseEntity<?>FiltroPorTermino(@RequestParam String search){
		List<Paciente> lista = pacienteService.ListarPorTermino(search);
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listarPaciente")
	public String ListarMisCitas(Model model ,String search) {
		List<Paciente> lista = new ArrayList<Paciente>();
		if(search.matches("^[0-9]{1,8}$")) {
			lista = pacienteService.findAllPacienteByDNI(search);
		}else {
			lista = pacienteService.findPacienteByNombreLike(search);
		}
		model.addAttribute("pacientes" , lista);
		
		return "listado/ListadoPacientes";
	}
}
