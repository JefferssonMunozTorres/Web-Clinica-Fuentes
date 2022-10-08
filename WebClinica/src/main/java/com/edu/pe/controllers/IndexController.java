package com.edu.pe.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.pe.models.*;
import com.edu.pe.services.*;
import com.edu.pe.utils.Utileria;


@Controller
public class IndexController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPersonalService personalService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Utileria utils;
	
	@Autowired
	private HttpSession sesion;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	@PersistenceContext
	private EntityManager em;
	private Long ver = new Date().getTime();
	
	@GetMapping("/")
	public String Inicio() {
		return "login";
	}
	
	@GetMapping("/home")
	public String LogearseGET(Model model , RedirectAttributes atributes) {
		Personal p = personalService.BuscarPorUsuario(utils.UsuarioLogeado());

		GregorianCalendar calendario = utils.FechaActual();

		sesion.setAttribute("nombreLogeado", p.NombresCompletos());
		sesion.setAttribute("userLogeado", p);
		
		model.addAttribute("saludo","Te damos la Bienvenida : "+ p.NombresCompletos());
		model.addAttribute("cantMedicos", personalService.CantidadPersonal("Medico"));
		model.addAttribute("cantVentas", personalService.CantidadPersonal("Ventas"));
		model.addAttribute("cantRecepcion", personalService.CantidadPersonal("Recepcion"));
		model.addAttribute("cantFarmaceutico", personalService.CantidadPersonal("Farmaceutico"));
		model.addAttribute("ver", ver);
		return "Home";
	}

	@PostMapping("/logout")
	public String CerrarSesion() {
		return "redirect:/";
	}
	
	@GetMapping("/config")
	public String VistaConfig() {
		
		return "Configuracion";
	}
	
	@PostMapping("/cambiar")
	public ResponseEntity<Boolean>CambiarClave(String actual , String nueva){
		Usuario user = usuarioService.BuscarPorNombreUsuario(utils.UsuarioLogeado());
		
		boolean estado = false;
		
		if(user !=null && encoder.matches(actual, user.getPass())) {
			user.setPass(encoder.encode(nueva));
			estado = usuarioService.Guardar(user);
		}

		return ResponseEntity.ok(estado);
	}


}
