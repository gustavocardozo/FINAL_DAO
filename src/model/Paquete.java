package model;

import java.io.Serializable;

public class Paquete implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected String nombre;
	protected float precio;
	protected Integer cantidadPersonas;
	protected String descripcion;
	protected Destino desde;
	protected Destino hacia;
	

	public Destino getDesde() {
		return desde;
	}
	public void setDesde(Destino desde) {
		this.desde = desde;
	}
	public Destino getHacia() {
		return hacia;
	}
	public void setHacia(Destino hasta) {
		this.hacia = hasta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
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

}
