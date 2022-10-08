package com.edu.pe.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.edu.pe.models.*;
import com.edu.pe.services.IHoraService;
import com.edu.pe.services.IHorarioPersonalService;
import com.edu.pe.services.IPersonalService;
import com.edu.pe.services.ITurnoService;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
	
	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private IPersonalService personalService;
	
	@Autowired
	private IHorarioPersonalService horarioPersService;
	
	@Autowired
	private IHoraService horaService;
	
	@GetMapping(value = "/")
	public String ViewHorarios(Model model) {
		List<Turno> lista = turnoService.ListarTodos();
		model.addAttribute("turnos", lista);

		return "MantHorarioPersonal";
	}
	
	@GetMapping("/listar/{id}")
	public String ListarTodos(Model model , @PathVariable("id") int idTurno) {
		List<Personal> lista = horarioPersService.ListadoPersonalPorTurno(idTurno);
	
		model.addAttribute("idTurno" , idTurno);
		model.addAttribute("personales" , lista);
		
		return "listado/ListadoPersonalPorTurno";
	}
	
	@DeleteMapping("/eliminarTurno")
	public ResponseEntity<?> EliminarTurnoPersonal(@RequestParam("idTurno") int idTurno , @RequestParam("idPersonal") int idPersonal) {
		boolean estado = false;
		
		HorarioPersonal objHorario = horarioPersService.BuscarPorTurnoPersonal(idPersonal, idTurno);
		
		if(objHorario !=null) {
			 estado = horarioPersService.EliminarHorario(objHorario.getId_horario());
		}

		return ResponseEntity.ok(estado);
	}
	
    @GetMapping("/filtrarMedicosTurno/{id}")
    @ResponseBody
    public List<Map<String, Object>> ListarMedicosDispTurno(@PathVariable("id") int idTurno) {
  
        List<Map<String, Object>> data = horarioPersService.ListadoMedicosDispTurno(idTurno);
        return data;
    }
	
    @GetMapping("/filtrarMedicosDisp/{id}")
    @ResponseBody
    public List<Personal> ListarMedicosDisponibles(@PathVariable("id") int idTurno) {
    	List<Personal> lista = horarioPersService.ListadoPersonalPorTurno(idTurno);
       // List<Map<String, Object>> data = horarioPersService.ListadoMedicosDispTurno(idTurno);
        return lista;
    }
    
	@PostMapping("/guardar")
	public ResponseEntity<?> GuardarHorario(@RequestParam("idTurno") int idTurno , @RequestParam("idPersonal") int idPersonal) {
		boolean estado = false;
		
		Personal objPers = personalService.BuscarPorId(idPersonal);
		Turno objTurno = turnoService.BuscarPorId(idTurno);
		
		if(objPers !=null && objTurno!=null) {
			HorarioPersonal hrsPers = new HorarioPersonal();
			hrsPers.setTurno(objTurno);
			hrsPers.setPersonal(objPers);
			
			estado = horarioPersService.Guardar(hrsPers);
		}

		return ResponseEntity.ok(estado);
	}

	@GetMapping("/filtrarHorariosDisp")
    @ResponseBody
    public List<Hora> ListarHorariosDisponibles(@Param("idTurno") int idTurno,
    		@Param("idMedico") int idMedico,
			@Param("fecha") String fecha) {

        List<Hora> data;
        
        try {
        	data= horaService.ListadoHorariosDisp(idTurno, idMedico, fecha);
        }catch(Exception ex){
        	data = new ArrayList<>();
        }
        return data;
    }
}
