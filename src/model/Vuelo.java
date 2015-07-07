package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vuelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected Avion avion;
	protected Destino desde;
	protected Destino hacia;
	protected Date partida;
	protected Date llegada;
	protected float precio;
	protected Integer disponibilidad;
	
	public Integer getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Integer disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Destino getDesde() {
		return desde;
	}
	public void setDesde(Destino desde) {
		this.desde = desde;
	}
	public Destino getHacia() {
		return hacia;
	}
	public void setHacia(Destino hacia) {
		this.hacia = hacia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public Date getPartida() {
		return partida;
	}
	public void setPartida(Date partida) {
		this.partida = partida;
	}
	public Date getLlegada() {
		return llegada;
	}
	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
		
		return sdf.format(partida)+ "-"+sdf.format(llegada)+";"+id;
	}
	
	
}
