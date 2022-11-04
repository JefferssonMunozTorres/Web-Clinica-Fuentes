package com.edu.pe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_producto;

	private String nombre;

	private Double precio_unitario;

	private int stock;

	private String imagen;

	public int getId_producto() {
		return id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPrecio_unitario() {
		return precio_unitario;
	}

	public int getStock() {
		return stock;
	}

	public String getImagen() {
		return imagen;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio_unitario(Double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
