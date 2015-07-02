package model;

import java.io.Serializable;

public class Paquete implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected String nombre;
	protected float precio;
	protected Vuelo vuelo;
	protected Integer cantidadPersonas;
	
	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}	
}
