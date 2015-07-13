package model;

import java.util.ArrayList;


public class Reserva {

	private int id;
	private float total;
	private Paquete paquete;
	private Vuelo vuelo;
	
	public Vuelo getVuelo() {
		return this.vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	private ArrayList<Cliente> clientes;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal() {
		return this.total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Paquete getPaquete() {
		return this.paquete;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	public ArrayList<Cliente> getClientes() {
		return this.clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
}
