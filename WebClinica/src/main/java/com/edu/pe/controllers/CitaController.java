package com.edu.pe.controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.pe.models.Cita;
import com.edu.pe.models.DetalleCita;
import com.edu.pe.models.Personal;
import com.edu.pe.models.Turno;
import com.edu.pe.services.ICitaService;
import com.edu.pe.services.IDetalleCitaService;
import com.edu.pe.services.IPersonalService;
import com.edu.pe.services.ITurnoService;

import com.edu.pe.utils.*;;

@Controller
@RequestMapping("/cita")
public class CitaController {
	
	@Autowired
	private ICitaService citaService;
	
	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private IPersonalService personalService;
	
	@Autowired
	private IDetalleCitaService detalleCitaService;
	
	@Autowired
	private Utileria utils;
	
	@GetMapping("/")
	public String MisCitas() {
		return "MisCitas";
	}
	
	@GetMapping("/listarCitasMedico")
	public String ListarMisCitas(Model model ,Cita c) {
		Personal p = personalService.BuscarPorUsuario(utils.UsuarioLogeado());
		
		List<Cita> lista = citaService.ListarPorFechasYPersonal(c.getFecha(), p.getId_personal(),"Pendiente");
		model.addAttribute("citas" , lista);
		
		return "listado/ListadoCitasPorMedico";
	}

	@GetMapping("/todos")
	public String VistaCitas() {
		return "MantCita";
	}
	
	@GetMapping("/nuevo")
	public String Nuevo(Model model) {
		List<Turno> listaTurno = turnoService.ListarTodos();
		
		model.addAttribute("turnos" , listaTurno);
		return "RegistrarCita";
	}
	
	@GetMapping("/listar")
	public String ListarTodos(Model model ,Cita c) {
		List<Cita> lista = citaService.ListarPorFechas(c.getFecha());
		model.addAttribute("citas" , lista);
		
		return "listado/ListadoCitas";
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> GuardarCita(Cita c){
		boolean estado = false;
		
		try {
			c.setEstado("Pendiente");
			
			estado = citaService.Guardar(c);
		}catch(Exception ex){
		}
		
		return ResponseEntity.ok(estado);
	}
	
	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> Eliminar(@PathVariable("id") int id){
		Cita e = citaService.BuscarPorId(id);
		boolean res;
		
		if(e !=null) {
			res = citaService.Eliminar(id);
			
			return ResponseEntity.ok(res);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/atender/{nro}")
	public String AtenderCitas(@PathVariable("nro") int nro , RedirectAttributes attributes,Model model) {
		Cita c = citaService.BuscarPorId(nro);
		
		if(c!=null) {
			model.addAttribute("cita", c);
			return "AtenderCita";
		}else {
			attributes.addFlashAttribute("error", "No se encontro ninguna cita con el nro "+nro);
			return "redirect:/cita/";
		}
	}
	
	@PostMapping("/guardarAtencion")
	public String GuardarAtenderCita(DetalleCita d , RedirectAttributes attributes) {
		Cita c = d.getCita();
		boolean estado = citaService.Guardar(c);
		
		if(c.getEstado().equals("Cancelado")) {
			attributes.addFlashAttribute("danger", "La cita con nro "+c.getNro_cita()+" se cancelo corectamente");
		}else {
			if(estado) {
				detalleCitaService.Guardar(d);
				attributes.addFlashAttribute("success", "Datos guardados correctamente de la cita nro "+c.getNro_cita());
			}else {
				attributes.addFlashAttribute("error", "No se ha podido guardar datos de la cita "+c.getNro_cita());
			}
		}
		return "redirect:/cita/";
	}

	
}
