package com.edu.pe.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.edu.pe.models.Perfil;
import com.edu.pe.models.Personal;
import com.edu.pe.models.Usuario;
import com.edu.pe.services.IPerfilService;
import com.edu.pe.services.IPersonalService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private IPersonalService personalService;

	@Autowired
	private IPerfilService perfilService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration freemarkerConfig;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/")
	public String Inicio(Model model) {
		List<Perfil> listaPerfil = perfilService.ListarTodos();

		model.addAttribute("perfiles", listaPerfil);
		return "MantPersonal";
	}

	@GetMapping("/nuevo")
	public String Registrar(Model model) {
		List<Perfil> listaPerfil = perfilService.ListarTodos();

		model.addAttribute("perfiles", listaPerfil);
		model.addAttribute("personal", new Personal());
		return "RegistrarPersonal";
	}

	@PostMapping("/guardar")
	@ResponseBody
	public boolean GuardarRegistro(Personal p) {
		Usuario user = p.getUsuario();
		
		user.setEstado(true);
		user.setPass(encoder.encode(p.getDni()));//ENCRYPTAMIENTO

		Perfil perf = perfilService.ObtenerPorId(user.getPerfil().getId_perfil());

		user.setPerfil(perf);
		user.setUsername(personalService.GenerarUsuario(perf.getNombre_perfil()));
		p.setUsuario(user);

		boolean estado = personalService.Guardar(p);

		if (estado) {
			EnviarCorreo(p.getCorreo(), p.NombresCompletos(), user.getUsername(), p.getDni());
		}

		return estado;
	}

	@GetMapping("/listarPersonales/{id}")
	public String ListarTodos(Model model, @PathVariable("id") int id) {
		List<Personal> lista = personalService.ListarPorPerfil(id);
		model.addAttribute("personales", lista);

		return "listado/ListadoPersonalPorPerfil";
	}

	@GetMapping("/cambiarEstado")
	@ResponseBody
	public ResponseEntity<?> CambiarEstado(@RequestParam("id") int id, @RequestParam("estado") boolean estado) {
		Personal objPers = personalService.BuscarPorId(id);

		if (objPers != null) {
			Usuario user = objPers.getUsuario();
			user.setEstado(!estado);
			objPers.setUsuario(user);
			boolean res = personalService.Guardar(objPers);
			return ResponseEntity.ok(res);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> Eliminar(@PathVariable("id") int id) {
		Personal e = personalService.BuscarPorId(id);
		boolean res;

		if (e != null) {
			res = personalService.Eliminar(id);

			return ResponseEntity.ok(res);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	public boolean EnviarCorreo(String correo, String nombres, String usuario, String pass) {
		boolean estado = false;

		try {
			Calendar calendario = Calendar.getInstance();
			Map<String, Object> model = new HashMap();
			model.put("nombres", nombres);
			model.put("usuario", usuario);
			model.put("pass", pass);
			model.put("anio", calendario.get(Calendar.YEAR));

			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

			Template t = freemarkerConfig.getTemplate("Plantilla-UsuarioSistema.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			SimpleMailMessage email = new SimpleMailMessage();

			helper.setTo(correo);
			helper.setText(html, true);
			helper.setSubject("Usuario sistema");

			mailSender.send(message);

			estado = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (estado) {
			System.out.println("Correo enviado");
		} else {
			System.out.println("Correo no fue enviado");
		}

		return estado;
	}

}
