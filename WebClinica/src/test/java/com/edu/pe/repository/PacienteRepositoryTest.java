package com.edu.pe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import com.edu.pe.models.Paciente;

@DataJpaTest
class PacienteRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Test
	void testResgistrarPaciente() {
		
		Paciente paciente1 = new Paciente();
		paciente1.setId_paciente(1);
		paciente1.setNombres("Julio Sebastian");
		paciente1.setApellidos("Duran Tomillo");
		paciente1.setDni("71928374");
		paciente1.setGenero("Masculino");
		paciente1.setDireccion("Av. Del Mar 162 - San Borja");
		paciente1.setCelular("985438274");
		paciente1.setCorreo("julio162@gmail.com");
		
		entityManager.merge(paciente1);
		entityManager.flush();
		
		assertNotNull(pacienteRepository.save(paciente1));
		
	}
	
	
	@Test
	void testListarPacientes() {
		
		Paciente paciente2 = new Paciente();
		paciente2.setId_paciente(2);
		paciente2.setNombres("Pablo");
		paciente2.setApellidos("Torres");
		
		Paciente paciente3 = new Paciente();
		paciente3.setId_paciente(3);
		paciente3.setNombres("Daniel");
		paciente3.setApellidos("Martinez");
		
		Paciente paciente4 = new Paciente();
		paciente4.setId_paciente(4);
		paciente4.setNombres("Piero");
		paciente4.setApellidos("Villegas");
		
		entityManager.merge(paciente2);
		entityManager.merge(paciente3);
		entityManager.merge(paciente4);
		entityManager.flush();
		
		List<Paciente> listaEnviada1 = new ArrayList<Paciente>();
		listaEnviada1.add(paciente2);
		
		
		List<Paciente> listaEnviada2 = new ArrayList<Paciente>();
		listaEnviada2.add(paciente4);
		
		assertEquals(listaEnviada1.get(0).getNombres(),pacienteRepository.ListarPorTermino("To").get(0).getNombres());
		
		assertEquals(listaEnviada2.get(0).getNombres(),pacienteRepository.ListarPorTermino("Vi").get(0).getNombres());
		
	}
	
}
