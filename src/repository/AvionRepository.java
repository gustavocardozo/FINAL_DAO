package repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import core.Base;
import core.IBase;
import model.Avion;

public class AvionRepository extends Archivo<Avion> implements IBase<Avion> {

	public AvionRepository() {
		super(Avion.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Avion> ListadoBase() {
		try {
			ArrayList<Avion> listado = new ArrayList<Avion>();
			String query = "SELECT ID,NOMBRE,CAPACIDAD FROM AVION";

			ResultSet rs = Base.ExecuteQuery(query);

			while (rs.next()) {
				Avion avion = new Avion();

				avion.setCapacidad(rs.getInt("CAPACIDAD"));
				avion.setId(rs.getInt("ID"));
				avion.setNombre(rs.getString("NOMBRE"));

				listado.add(avion);

			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Avion>();
		}
	}

	@Override
	public Avion GetByIdBase(Integer id) {
		try {
			Avion avion = new Avion();
			String query = "SELECT ID, NOMBRE, CAPACIDAD FROM AVION WHERE ID="
					+ id;
			ResultSet rs = Base.ExecuteQuery(query);

			if(rs.next())
			{
				avion.setCapacidad(rs.getInt("CAPACIDAD"));
				avion.setId(rs.getInt("ID"));
				avion.setNombre(rs.getString("NOMBRE"));
			}

			return avion;

		} catch (Exception e) {
			e.printStackTrace();
			return new Avion();
		}
	}

	@Override
	public Boolean InsertarBase(Avion t) {
		try {
			String parametros = t.getId()+",'" + t.getNombre() + "', " + t.getCapacidad();
			String script = "INSERT INTO AVION (ID,NOMBRE, CAPACIDAD) VALUES("+parametros+")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Avion t) {
		try {
			String script = "UPDATE AVION SET NOMBRE = '"+t.getNombre()+"',CAPACIDAD = "+t.getCapacidad()+" WHERE ID= "+t.getId();
			return Base.ExecuteScript(script);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean DeleteBase(Avion t) {
		try {
			String script = "DELETE AVION WHERE ID="+ t.getId();
			return Base.ExecuteScript(script);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		try {
			
			String query = "SELECT MAX(ID)+1 AS ID FROM AVION";
			
			ResultSet rs = Base.ExecuteQuery(query);
			
			if(rs.next())
			{
				return rs.getInt("ID");
				
			}
			return -1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
