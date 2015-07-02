package model;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected String dni;
	protected String nombre;
	protected String direccion;
	protected String email;
	protected String telefono;
	protected Date fecha_nacimiento;
	protected String apellido;
	
	public Cliente(Integer id, String dni, String nombre, String direccion, String email, String telefono, Date fecha_nacimiento, String apellido)
	{
		setId(id);
		setDni(dni);
		setNombre(nombre);
		setDireccion(direccion);
		setEmail(email);
		setTelefono(telefono);
		setFecha_nacimiento(fecha_nacimiento);
		setApellido(apellido);
		
	}
	
	public Cliente(){}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	}
