package com.edu.pe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import com.edu.pe.models.HorarioPersonal;
import com.edu.pe.models.Perfil;
import com.edu.pe.models.Personal;
import com.edu.pe.models.Turno;
import com.edu.pe.models.Usuario;


//@SpringBootTest(classes = PersonalRepositoryTest.class)
//@AutoConfigureTestDatabase
@DataJpaTest
//@AutoConfigureTestDatabase(replace = NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PersonalRepositoryTest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private PersonalRepository personalRepository;
	
	@Autowired
	private HorarioPersonalRepository horarioPersonalRepository;
	
	
	@Test
	void testRegistrarPersonal() {
		
		Personal personal1 = new Personal();
		personal1.setId_personal(1);
		personal1.setNombre("Julian");
		personal1.setApellido("Malaga");
		personal1.setDni("974384752");
		personal1.setCorreo("julianmalaga@empresa.com");
		personal1.setGenero("Masculino");
		
		entityManager.merge(personal1);
		entityManager.flush();
		
		assertNotNull(personalRepository.save(personal1));
		
	}
	
	
	@Test
	void testListarPorPerfil() {
		
		Personal personal1 = new Personal();
		personal1.setNombre("Julian");
		personal1.setApellido("Malaga");
		personal1.setId_personal(2);
		
		Usuario usuario1 = new Usuario();
		usuario1.setId_usuario(1);
		
		Perfil perfil1 = new Perfil();
		perfil1.setId_perfil(1); 		
		perfil1.setNombre_perfil("Administrador");
		
		Perfil perfil2 = new Perfil();
		perfil2.setId_perfil(2);		
		perfil2.setNombre_perfil("Tecnico");
		
		usuario1.setPerfil(perfil1);
		personal1.setUsuario(usuario1);
		
		
		List<Personal> listaEnviada = new ArrayList<Personal>();
		listaEnviada.add(personal1);
		
		entityManager.merge(perfil1);
		entityManager.merge(perfil2);
		entityManager.merge(usuario1);
		entityManager.merge(personal1);
		entityManager.flush();
		
		assertEquals(listaEnviada.get(0).getNombre(), personalRepository.ListarPorPerfil(1).get(0).getNombre());
		
	}
	
	
	@Test
	void testAsignarTurno() {
		
		Turno turno1 = new Turno();
		turno1.setId_turno(1);
		turno1.setNombre("Mañana");
		turno1.setHora_inicio("9:00");
		turno1.setHora_fin("12:00");
		
		Turno turno2 = new Turno();
		turno2.setId_turno(2);
		turno2.setNombre("Tarde");
		turno2.setHora_inicio("12:00");
		turno2.setHora_fin("18:00");
		
		Personal personal1 = new Personal();
		personal1.setId_personal(3);
		personal1.setNombre("Julian");
		personal1.setApellido("Malaga");
		personal1.setDni("974384752");
		personal1.setCorreo("julianmalaga@empresa.com");
		personal1.setGenero("Masculino");
		
		HorarioPersonal horarioPersonal = new HorarioPersonal();
		horarioPersonal.setId_horario(1);
		horarioPersonal.setPersonal(personal1);
		horarioPersonal.setTurno(turno2);
		
		entityManager.merge(turno1);
		entityManager.merge(turno2);
		entityManager.merge(personal1);
		entityManager.merge(horarioPersonal);
		entityManager.flush();
		
		assertNotNull(horarioPersonalRepository.save(horarioPersonal));
		
	}
	
}
