package com.edu.pe.controllers;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;


import com.edu.pe.models.Perfil;
import com.edu.pe.models.Personal;
import com.edu.pe.models.Producto;
import com.edu.pe.services.IUploadFileService;
import com.edu.pe.services.ProductoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/")
	public String Inicio() {
		return "Productos";
	}
	
	@GetMapping("/nuevo")
	public String Registrar(Model model) {
		model.addAttribute("personal", new Personal());
		return "RegistrarProducto";
	}
	
	@GetMapping("/listar")
	public String ListarTodos(Model model) {
		List<Producto> lista = productoService.ListarTodos();
		
		model.addAttribute("productos" , lista);
		
		return "listado/ListadoProductos";
	}
	@GetMapping("/buscar")
	public String buscar(@RequestParam String search,Model model) {
		List<Producto> lista = new ArrayList<Producto>();
		if(search!=null&&!search.isEmpty()) {
			lista=productoService.findProductoByNombreLike(search);
		}else {
			lista=productoService.ListarTodos();
		}
		
		
		model.addAttribute("productos" , lista);
		
		return "listado/ListadoProductos";
	}
	
	@PostMapping("/guardar")
	@ResponseBody
	public String Guardar(@RequestParam("file") MultipartFile foto,@RequestParam("values") String values) {
		String mensj = "";
		boolean res;
		System.out.println("guardaaaaaer");
		try {
			int cantDNI =0;

			if(cantDNI > 0) {
				//mensj = "El DNI "+p.getDni()+" ya se encuentra registrado";
			}else {
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNodeResponse = mapper.readTree(values);
				Producto p=new Producto();
				p.setNombre(rootNodeResponse.path("nombre").asText());
				p.setPrecio_unitario(Double.parseDouble(rootNodeResponse.path("precio").asText()));
				p.setStock(Integer.parseInt(rootNodeResponse.path("stock").asText()));
				
				if (!foto.isEmpty()) {
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(foto);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.setImagen(uniqueFilename);
				}
				res = productoService.Guardar(p);
				if(res) {
					mensj = "OK";
				}else {
					mensj = "No se ha podido registrar producto";
				}
			}
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		

		return mensj;
	}
	
	@PostMapping("/actualizar")
	@ResponseBody
	public String actualizar(@RequestParam(value = "file", required = false) MultipartFile foto,@RequestParam("values") String values) {
		String mensj = "";
		boolean res;
		try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNodeResponse = mapper.readTree(values);
				Producto p=productoService.ObtenerPorId(Integer.parseInt(rootNodeResponse.path("id").asText()));
				if(p!=null) {
					p.setNombre(rootNodeResponse.path("nombre").asText());
					p.setPrecio_unitario(Double.parseDouble(rootNodeResponse.path("precio").asText()));
					p.setStock(Integer.parseInt(rootNodeResponse.path("stock").asText()));
					
					if (foto!=null&&!foto.isEmpty()) {
						if (p.getImagen() != null && p.getImagen().length() > 0) {
							uploadFileService.delete(p.getImagen());
						}
						
						String uniqueFilename = null;
						try {
							uniqueFilename = uploadFileService.copy(foto);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.setImagen(uniqueFilename);
					}
					res = productoService.Guardar(p);
					if(res) {
						mensj = "OK";
					}else {
						mensj = "No se ha podido registrar producto";
					}
				}
				
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		

		return mensj;
	}
	
	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> Eliminar(@PathVariable("id") int id){
		Producto e = productoService.ObtenerPorId(id);
		boolean res;
		
		if(e !=null) {
			res = productoService.Eliminar(id);
			
			return ResponseEntity.ok(res);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/buscarId")
	public ResponseEntity<?>BuscarProducto(@RequestParam int id){
		Producto p = productoService.ObtenerPorId(id);
		return ResponseEntity.ok(p);

	}
	
	@GetMapping("/listarTodos")
	public ResponseEntity<?>ListarPacintes(){
		List<Producto> lista = productoService.ListarTodos();
		
		return ResponseEntity.ok(lista);
	}
	

	@GetMapping("/filtro")
	public ResponseEntity<?>FiltroPorTermino(@RequestParam String search){
		List<Producto> lista = productoService.ListarPorTermino(search);
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/cargar-productos")
	public @ResponseBody List<Producto> cargarProductos(@RequestParam String term) {
		System.out.println("cargar-productos: "+term);
		return productoService.findProductoByNombreLike(term);
	}
}
