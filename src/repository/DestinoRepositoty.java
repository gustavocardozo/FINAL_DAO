package repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;
import core.Base;
import core.IBase;

public class DestinoRepositoty extends Archivo<Destino> implements IBase<Destino>{

	public DestinoRepositoty() {
		super(Destino.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Destino> ListadoBase() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Destino> listado = new ArrayList<Destino>();
			String query = "SELECT ID, NOMBRE, DESCRIPCION FROM DESTINOS";

			ResultSet rs = Base.ExecuteQuery(query);

			while (rs.next()) {
				Destino ciudad = new Destino();
				
				ciudad.setId(rs.getInt("ID"));
				ciudad.setNombre(rs.getString("NOMBRE"));
				ciudad.setDescripcion(rs.getString("DESCRIPCION"));

				listado.add(ciudad);

			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Destino>();
		}
	}

	@Override
	public Destino GetByIdBase(Integer id) {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT ID, NOMBRE, DESCRIPCION FROM DESTINOS WHERE ID="+id;
			ResultSet rs = Base.ExecuteQuery(query);
			Destino ciudad = new Destino();
			if(rs.next()){
				ciudad.setId(id);
				ciudad.setDescripcion(rs.getString("DESCRIPCION"));
				ciudad.setNombre(rs.getString("NOMBRE"));

			}
			return ciudad;
		} catch (Exception e) {
			e.printStackTrace();
			return new Destino();
		}
	}

	@Override
	public Boolean InsertarBase(Destino t) {
		// TODO Auto-generated method stub
		try {
			String parametros = t.getId()+ ",'"+t.getNombre()+"', '"+t.getDescripcion()+"' ";
			String script = "INSERT INTO DESTINOS(ID,NOMBRE,DESCRIPCION) VALUES("+ parametros + ")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Destino t) {
		// TODO Auto-generated method stub
		try {
			String script = "UPDATE DESTINOS SET NOMBRE='"+t.getNombre()+"', DESCRIPCION ='"+t.getDescripcion()+"' WHERE ID="+t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean DeleteBase(Destino t) {
		// TODO Auto-generated method stub
		try {
			String script = "DELETE CIUDADES WHERE ID=" + t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT MAX(ID)+1 AS IDENTIFICADOR FROM CIUDADES";
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

}
