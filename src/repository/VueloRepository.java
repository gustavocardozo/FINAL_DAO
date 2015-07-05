package repository;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import model.Vuelo;
import core.IBase;
import core.Base;

public class VueloRepository extends Archivo<Vuelo> implements IBase<Vuelo> {

	public VueloRepository() {
		super(Vuelo.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Vuelo> ListadoBase() {
		try {
			ArrayList<Vuelo> listado = new ArrayList<Vuelo>();
			String query = "SELECT * FROM VUELO";
			ResultSet rs  = Base.ExecuteQuery(query);
			AvionRepository repository = new AvionRepository();
			
			while (rs.next()) {
				
				Vuelo vuelo = new Vuelo();
				
				vuelo.setId(rs.getInt("ID"));
				vuelo.setLlegada(rs.getDate("HORARIO_LLEGADA"));
				vuelo.setPartida(rs.getDate("HORARIO_PARTIDA"));
				vuelo.setAvion(repository.GetByIdBase(rs.getInt("ID_AVION")));
				vuelo.setDesde(rs.getString("DESDE"));
				vuelo.setHacia(rs.getString("HACIA"));
				vuelo.setHacia(rs.getString("DESCRIPCION"));
				
				listado.add(vuelo);
			}
			return listado;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Vuelo>();
		}
	}

	@Override
	public Vuelo GetByIdBase(Integer id) {
		try {

			String query = "SELECT * FROM VUELO WHERE ID="+id;
			ResultSet rs  = Base.ExecuteQuery(query);
			AvionRepository repository = new AvionRepository();
			Vuelo vuelo = new Vuelo();
			
			if(rs.next())
			{
				vuelo.setId(rs.getInt("ID"));
				vuelo.setLlegada(rs.getDate("HORARIO_LLEGADA"));
				vuelo.setPartida(rs.getDate("HORARIO_PARTIDA"));
				vuelo.setAvion(repository.GetByIdBase(rs.getInt("ID_AVION")));
				vuelo.setDesde(rs.getString("DESDE"));
				vuelo.setHacia(rs.getString("HACIA"));
				vuelo.setHacia(rs.getString("DESCRIPCION"));
			}
			
			return vuelo;
		} catch (Exception e) {
			e.printStackTrace();
			return new Vuelo();
		}
	}

	@Override
	public Boolean InsertarBase(Vuelo t) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String parametros = t.getId()+", '"+sdf.format(t.getLlegada())+"', '"+sdf.format(t.getPartida())+"', "+t.getAvion().getId()+",'"+
			t.getDesde()+"','"+t.getHacia()+"',"+t.getPrecio();
			String script = "INSERT INTO VUELO(ID,HORARIO_LLEGADA,HORARIO_PARTIDA,ID_AVION,DESDE,HACIA,PRECIO) VALUES("+parametros+")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Vuelo t) {
		try {
			if(this.DeleteArchivo(t))
			{
				if(this.InsertarArchivo(t))
				{
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean DeleteBase(Vuelo t) {
		try {
			String script = "DELETE VUELO WHERE ID="+t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		try {
			String query = "SELECT MAX(ID) AS ID FROM VUELO";
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