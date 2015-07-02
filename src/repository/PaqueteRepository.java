package repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Paquete;
import core.IBase;
import core.Base;

public class PaqueteRepository extends Archivo<Paquete> implements IBase<Paquete> {

	public PaqueteRepository() {
		super(Paquete.class);
		
	}
	VueloRepository vueloRepository;

	@Override
	public ArrayList<Paquete> ListadoBase() {
		try {
			ArrayList<Paquete> listado = new ArrayList<Paquete>();
			String query = "SELECT ID, NOMBRE, PRECIO, CANT_PERSONAS, ID_VUELO FROM PAQUETE";
			ResultSet rs = Base.ExecuteQuery(query);
			vueloRepository = new VueloRepository();
			while (rs.next()) {
				Paquete paquete  = new Paquete();
				
				paquete.setId(rs.getInt("ID"));
				paquete.setNombre(rs.getString("NOMBRE"));
				paquete.setPrecio(rs.getFloat("PRECIO"));
				paquete.setCantidadPersonas(rs.getInt("CANT_PERSONAS"));
				paquete.setVuelo(vueloRepository.GetByIdBase(rs.getInt("ID_VUELO")));
				
				listado.add(paquete);
				
			}
			
			return listado;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Paquete>();
		}
	}

	@Override
	public Paquete GetByIdBase(Integer id) {
		try {
			String query = "SELECT ID, NOMBRE, PRECIO, CANT_PERSONAS, ID_VUELO FROM PAQUETE WHERE ID="+id;
			Paquete paquete  = new Paquete();
			vueloRepository = new VueloRepository();
			ResultSet rs = Base.ExecuteQuery(query);
			
			if (rs.next()) {
				paquete.setId(rs.getInt("ID"));
				paquete.setNombre(rs.getString("NOMBRE"));
				paquete.setPrecio(rs.getFloat("PRECIO"));
				paquete.setCantidadPersonas(rs.getInt("CANT_PERSONAS"));
				paquete.setVuelo(vueloRepository.GetByIdBase(rs.getInt("ID_VUELO")));
			}
			return paquete;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Paquete();
		}
	}

	@Override
	public Boolean InsertarBase(Paquete t) {
		try {
			String parametros = t.getId()+",'" + t.getNombre() + "', " + t.getPrecio()+", "+t.getCantidadPersonas()+", "+t.getVuelo().getId();
			String script = "INSERT INTO AVION (ID,NOMBRE,PRECIO,CANT_PERSONAS,ID_VUELO) VALUES("+parametros+")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Paquete t) {
		try {
			String script = "UPDATE AVION SET NOMBRE='"+t.getNombre()+"',PRECIO="+t.getPrecio()+",CANT_PERSONAS="+t.getCantidadPersonas()+",ID_VUELO="+t.getVuelo().getId()+" WHERE ID="+t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean DeleteBase(Paquete t) {
		try {
			
			String script = "DELETE PAQUETE WHERE ID =" +t.getId();
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		try {
			String query = "SELECT MAX(ID)+1 AS ID FROM PAQUETE";
			ResultSet rs= Base.ExecuteQuery(query);
			if (rs.next()) {
				return rs.getInt("ID");
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
