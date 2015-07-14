package repository;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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
			SimpleDateFormat sdfPartida=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			SimpleDateFormat sdfLlegada=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			ArrayList<Vuelo> listado = new ArrayList<Vuelo>();
			String query = "SELECT * FROM VUELO";
			ResultSet rs  = Base.ExecuteQuery(query);
			AvionRepository repository = new AvionRepository();
			DestinoRepository repoDestino = new DestinoRepository();
			
			while (rs.next()) {
				
				Vuelo vuelo = new Vuelo();
				
				vuelo.setId(rs.getInt("ID"));
				vuelo.setLlegada(sdfLlegada.parse((rs.getString("HORARIO_LLEGADA"))));
				vuelo.setPartida(sdfPartida.parse((rs.getString("HORARIO_PARTIDA"))));
				vuelo.setAvion(repository.GetByIdBase(rs.getInt("ID_AVION")));
				vuelo.setDesde(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("DESDE"))));
				vuelo.setHacia(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("HACIA"))));
				vuelo.setPrecio(rs.getFloat("PRECIO"));
				vuelo.setDisponibilidad(GetDisponibilidad(vuelo));
				
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

			SimpleDateFormat sdfPartida=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			SimpleDateFormat sdfLlegada=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			String query = "SELECT * FROM VUELO WHERE ID="+id;
			ResultSet rs  = Base.ExecuteQuery(query);
			AvionRepository repository = new AvionRepository();
			Vuelo vuelo = new Vuelo();
			DestinoRepository repoDestino = new DestinoRepository();
			if(rs.next())
			{
				vuelo.setId(rs.getInt("ID"));
				vuelo.setLlegada(sdfLlegada.parse((rs.getString("HORARIO_LLEGADA"))));
				vuelo.setPartida(sdfPartida.parse((rs.getString("HORARIO_PARTIDA"))));
				vuelo.setAvion(repository.GetByIdBase(rs.getInt("ID_AVION")));
				vuelo.setDesde(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("DESDE"))));
				vuelo.setHacia(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("HACIA"))));
				vuelo.setPrecio(rs.getFloat("PRECIO"));
				vuelo.setDisponibilidad(GetDisponibilidad(vuelo));
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
			SimpleDateFormat sdfPartida=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdfLlegada=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String parametros = t.getId()+", '"+sdfLlegada.format(t.getLlegada())+"', '"+sdfPartida.format(t.getPartida())+"', "+t.getAvion().getId()+","+
			t.getDesde().getId()+","+t.getHacia().getId()+","+t.getPrecio();
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
			String query = "SELECT MAX(ID)+1 AS ID FROM VUELO";
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
	public ArrayList<Vuelo> VuelosBy(String where)
	{
		try {
			SimpleDateFormat sdfPartida=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			SimpleDateFormat sdfLlegada=new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			ArrayList<Vuelo> listado = new ArrayList<Vuelo>();
			String query = "SELECT * FROM VUELO "+ where;
			ResultSet rs  = Base.ExecuteQuery(query);
			AvionRepository repository = new AvionRepository();
			DestinoRepository repoDestino = new DestinoRepository();

			
			while (rs.next()) {
				
				Vuelo vuelo = new Vuelo();
				
				vuelo.setId(rs.getInt("ID"));
				vuelo.setLlegada(sdfLlegada.parse((rs.getString("HORARIO_LLEGADA"))));
				vuelo.setPartida(sdfPartida.parse((rs.getString("HORARIO_PARTIDA"))));
				vuelo.setAvion(repository.GetByIdBase(rs.getInt("ID_AVION")));
				vuelo.setDesde(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("DESDE"))));
				vuelo.setHacia(repoDestino.GetByIdBase(Integer.parseInt(rs.getString("HACIA"))));
				vuelo.setPrecio(rs.getFloat("PRECIO"));
				vuelo.setDisponibilidad(GetDisponibilidad(vuelo));
				
				listado.add(vuelo);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Vuelo>();
		}
	}
	
	public Integer GetDisponibilidad(Vuelo vuelo)
	{
		try {
			String query = "SELECT A.CAPACIDAD - COUNT(*) AS CAPACIDAD FROM VUELO V INNER JOIN RESERVA R ON R.ID_VUELO = V.ID INNER JOIN AVION A ON A.ID=V.ID_AVION WHERE V.ID="+ vuelo.getId();
			ResultSet rs  = Base.ExecuteQuery(query);
			
			if(rs.next())
			{
				return rs.getInt("CAPACIDAD");
			}
			else
			{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
