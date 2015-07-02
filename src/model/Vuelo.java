package model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Vuelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected Avion avion;
	protected String desde;
	protected String hacia;
	protected Date partida;
	protected Date llegada;
	
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHacia() {
		return hacia;
	}
	public void setHacia(String hacia) {
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
