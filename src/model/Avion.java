package model;

import java.io.Serializable;

public class Avion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected String nombre;
	protected Integer capacidad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	


}
