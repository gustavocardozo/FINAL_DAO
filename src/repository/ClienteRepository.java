package repository;

import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Cliente;
import core.IBase;
import core.Base;

public class ClienteRepository extends Archivo<Cliente> implements
		IBase<Cliente> {

	public ClienteRepository() {
		super(Cliente.class);
	}

	@Override
	public ArrayList<Cliente> ListadoBase() {
		try {
			ArrayList<Cliente> listado = new ArrayList<Cliente>();
			String query = "SELECT ID, DIRECCION, EMAIL, TELEFONO, DNI, NOMBRE, APELLIDO, FECHA_NACIMIENTO FROM CLIENTE";

			ResultSet rs = Base.ExecuteQuery(query);

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("ID"));
				cliente.setDni(rs.getString("DNI"));
				cliente.setNombre(rs.getString("NOMBRE"));
				cliente.setApellido(rs.getString("APELLIDO"));
				cliente.setDireccion(rs.getString("DIRECCION"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setFecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"));
				cliente.setTelefono(rs.getString("TELEFONO"));

				listado.add(cliente);

			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Cliente>();
		}
	}

	@Override
	public Cliente GetByIdBase(Integer id) {
		try {
			String query = "SELECT ID, DIRECCION, EMAIL, TELEFONO, DNI, NOMBRE, APELLIDO, FECHA_NACIMIENTO FROM CLIENTE WHERE ID="+id;
			ResultSet rs = Base.ExecuteQuery(query);
			Cliente cliente = new Cliente();
			if(rs.next()){
				cliente.setId(id);
				cliente.setDni(rs.getString("DNI"));
				cliente.setNombre(rs.getString("NOMBRE"));
				cliente.setApellido(rs.getString("APELLIDO"));
				cliente.setDireccion(rs.getString("DIRECCION"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setFecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"));
				cliente.setTelefono(rs.getString("TELEFONO"));
			}
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return new Cliente();
		}
	}

	@Override
	public Boolean InsertarBase(Cliente t) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String parametros = t.getId()+ ",'"+t.getDireccion()+"', '"+t.getEmail()+"', '"+t.getTelefono()+"','"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellido()+"','"+ sdf.format(t.getFecha_nacimiento())+"'";
			String script = "INSERT INTO CLIENTE(ID,DIRECCION,EMAIL,TELEFONO,DNI,NOMBRE,APELLIDO,FECHA_NACIMIENTO) VALUES("+ parametros + ")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Cliente t) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String script = "UPDATE CLIENTE SET DIRECCION='"+t.getDireccion()+"', EMAIL ='"+t.getEmail()+"', TELEFONO='"+t.getTelefono()+"', "
					+ "DNI='"+t.getDni()+"', NOMBRE="+t.getNombre()+",APELLIDO='"+t.getApellido()+"',FECHA_NACIMIENTO='"+ sdf.format(t.getFecha_nacimiento())+"' WHERE ID="+t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean DeleteBase(Cliente t) {
		try {
			String script = "DELETE CLIENTE WHERE ID=" + t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		try {
			String query = "SELECT MAX(ID)+1 AS IDENTIFICADOR FROM CLIENTE";
			ResultSet rs = Base.ExecuteQuery(query);
			
			if(rs.next())
			{
				return rs.getInt("IDENTIFICADOR"); 
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void DeleteArchivos()
	{
		
		try {
			File fichero = new File("Cliente.obj");
			
			if(fichero.exists())
			{
				fichero.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
