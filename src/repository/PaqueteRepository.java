package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			String query = "SELECT ID, NOMBRE, PRECIO, CANT_PERSONAS, DESCRIPCION, DESDE, HACIA FROM PAQUETE";
			ResultSet rs = Base.ExecuteQuery(query);
			vueloRepository = new VueloRepository();
			DestinoRepository repository = new DestinoRepository();
			while (rs.next()) {
				Paquete paquete  = new Paquete();
				
				paquete.setId(rs.getInt("ID"));
				paquete.setNombre(rs.getString("NOMBRE"));
				paquete.setPrecio(rs.getFloat("PRECIO"));
				paquete.setCantidadPersonas(rs.getInt("CANT_PERSONAS"));
				paquete.setDescripcion(rs.getString("DESCRIPCION"));
				paquete.setHacia(repository.GetByIdBase(rs.getInt("HACIA")));
				paquete.setDesde(repository.GetByIdBase(rs.getInt("DESDE")));
				
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
			String query = "SELECT ID, NOMBRE, PRECIO, CANT_PERSONAS, DESCRIPCION,DESDE,HACIA FROM PAQUETE WHERE ID="+id;
			Paquete paquete  = new Paquete();
			vueloRepository = new VueloRepository();
			ResultSet rs = Base.ExecuteQuery(query);
			DestinoRepository repository = new DestinoRepository();
			
			if (rs.next()) {
				paquete.setId(rs.getInt("ID"));
				paquete.setNombre(rs.getString("NOMBRE"));
				paquete.setPrecio(rs.getFloat("PRECIO"));
				paquete.setCantidadPersonas(rs.getInt("CANT_PERSONAS"));
				paquete.setDescripcion(rs.getString("DESCRIPCION"));
				paquete.setHacia(repository.GetByIdBase(rs.getInt("HACIA")));
				paquete.setDesde(repository.GetByIdBase(rs.getInt("DESDE")));
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
			String parametros = t.getId()+",'"+ t.getNombre() + "', " + t.getPrecio()+", "+t.getCantidadPersonas()+", '"+t.getDescripcion()+"',"+t.getDesde().getId()+","+t.getHacia().getId();
			String script = "INSERT INTO PAQUETE(ID,NOMBRE,PRECIO,CANT_PERSONAS,DESCRIPCION,DESDE,HACIA) VALUES("+parametros+")";
			return Base.ExecuteScript(script);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean ModificarBase(Paquete t) {
		try {
			String script = "UPDATE AVION SET NOMBRE='"+t.getNombre()+"',PRECIO="+t.getPrecio()+",CANT_PERSONAS="+t.getCantidadPersonas()+",DESCRIPCION='"+t.getDescripcion()+"',DESDE="+t.getDesde().getId()+","+t.getHacia().getId()+" WHERE ID="+t.getId();
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
	public ArrayList<Paquete> PaquetesBy(String where)
	{
		try {
			
			ArrayList<Paquete> listado = new ArrayList<Paquete>();
			String query = "SELECT ID, NOMBRE, PRECIO, CANT_PERSONAS, DESCRIPCION, DESDE, HACIA FROM PAQUETE "+where;
			ResultSet rs = Base.ExecuteQuery(query);
			vueloRepository = new VueloRepository();
			DestinoRepository repository = new DestinoRepository();
			while (rs.next()) {
				Paquete paquete  = new Paquete();
				
				paquete.setId(rs.getInt("ID"));
				paquete.setNombre(rs.getString("NOMBRE"));
				paquete.setPrecio(rs.getFloat("PRECIO"));
				paquete.setCantidadPersonas(rs.getInt("CANT_PERSONAS"));
				paquete.setDescripcion(rs.getString("DESCRIPCION"));
				paquete.setHacia(repository.GetByIdBase(rs.getInt("HACIA")));
				paquete.setDesde(repository.GetByIdBase(rs.getInt("DESDE")));
				
				listado.add(paquete);
				
			}
			
			return listado;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Paquete>();
		}
		
	}
	
	public List<Paquete> listaParaMigrar() {
		List<Paquete> archivo = null;
		List<Paquete> base = null;
		try{
			archivo = ListadoArchivo();
			base = ListadoBase();
			
			
			//archivo.removeAll(base);
			
			Iterator<Paquete> itArchivo = archivo.iterator();
			Iterator<Paquete> itBase = base.iterator();
			while(itArchivo.hasNext()){
				Paquete paqueteArchivo = (Paquete)itArchivo.next();
				while(itBase.hasNext()){
					Paquete paqueteBase = (Paquete)itBase.next();
					if(paqueteArchivo.getId() == paqueteBase.getId())
					{
						itArchivo.remove();
						break;
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			return archivo;
		}
		
	}

}
