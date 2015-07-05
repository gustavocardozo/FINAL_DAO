package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Destino implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String Nombre;
	private String Descripcion;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	@Override
	public String toString() {
		return this.Nombre+";"+this.Id;
	}
	
}
