package com.edu.pe.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class Utileria {

	public String UsuarioLogeado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		
        return userName;
    }
	
	public GregorianCalendar FechaActual() {
		Calendar ahora = Calendar.getInstance();
		int anio = ahora.get(Calendar.YEAR);
		int mes = ahora.get(Calendar.MONTH);
		int dia = ahora.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar calendario = new GregorianCalendar(anio , mes, dia);
		
		return calendario;
	}
}
