package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import model.Cliente;
import model.Reserva;
import core.IBase;
import core.Base;

public class ReservaRepository extends Archivo<Reserva> implements
		IBase<Reserva> {

	public ReservaRepository() {
		super(Reserva.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Reserva> ListadoBase() {
		try {
			ArrayList<Reserva> listado = new ArrayList<Reserva>();
			String queryReserva = "SELECT ID, ID_PAQUETE FROM RESERVA";
			ResultSet rsReserva = Base.ExecuteQuery(queryReserva);
			PaqueteRepository paqueteRepository = new PaqueteRepository();
			ClienteRepository clienteRepository = new ClienteRepository();
			
			while (rsReserva.next()) {
				Reserva reserva = new Reserva();
				
				String queryClientes = "SELECT ID_CLIENTE FROM RESERVA_CLIENTE WHERE ID_RESERVA="
						+ rsReserva.getInt("ID");
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				ResultSet rsClientes = Base.ExecuteQuery(queryClientes);

				while (rsClientes.next()) {
					clientes.add(clienteRepository.GetByIdBase(rsClientes.getInt("ID_CLIENTE")));
				}
				reserva.setClientes(clientes);
				reserva.setPaquete(paqueteRepository.GetByIdBase(rsReserva.getInt("ID_PAQUETE")));
				reserva.setTotal(reserva.getPaquete().getPrecio() * reserva.getClientes().size());
				listado.add(reserva);
			}
			
			return listado;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Reserva>();
		}
	}

	@Override
	public Reserva GetByIdBase(Integer id) {
		try {
			String queryReserva = "SELECT ID, ID_PAQUETE FROM RESERVA WHERE ID="+id;
			ResultSet rsReserva = Base.ExecuteQuery(queryReserva);
			PaqueteRepository paqueteRepository = new PaqueteRepository();
			ClienteRepository clienteRepository = new ClienteRepository();
			
			Reserva reserva = new Reserva();
			if(rsReserva.next()) {
				String queryClientes = "SELECT ID_CLIENTE FROM RESERVA_CLIENTE WHERE ID_RESERVA="
						+ rsReserva.getInt("ID");
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				ResultSet rsClientes = Base.ExecuteQuery(queryClientes);

				while (rsClientes.next()) {
					clientes.add(clienteRepository.GetByIdBase(rsClientes.getInt("ID_CLIENTE")));
				}
				reserva.setClientes(clientes);
				reserva.setPaquete(paqueteRepository.GetByIdBase(rsReserva.getInt("ID_PAQUETE")));
				reserva.setTotal(reserva.getPaquete().getPrecio() * reserva.getClientes().size());
			}
			
			return reserva;

		} catch (Exception e) {
			e.printStackTrace();
			return new Reserva();
		}
	}

	@Override
	public Boolean InsertarBase(Reserva t) {
		Boolean inserto=false;
		try {
			
			String parametros = t.getId()+", "+t.getPaquete().getId();
			String script = "INSERT INTO RESERVA(ID,ID_PAQUETE) VALUES("+parametros+")";
			
			if(Base.ExecuteScript(script))
			{
				inserto=true;
				for(Cliente cliente: t.getClientes())
				{
					inserto=false;
					String scriptClientes = "INSERT INTO RESERVA_CLIENTE(ID_RESERVA,ID_CLIENTE) VALUES("+t.getId()+","+cliente.getId()+")";
					Base.ExecuteScript(scriptClientes);
					inserto = true;
				}	
			}
			return inserto;
		} catch (Exception e) {
			e.printStackTrace();
			return inserto;
		}
	}

	@Override
	public Boolean ModificarBase(Reserva t) {
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
	public Boolean DeleteBase(Reserva t) {
		try {
			
			String scriptTablasIntermedias="DELETE PAQUETE_CLIENTE WHERE ID_PAQUETE="+t.getPaquete().getId()+"AND ID_CLIENTE IN(";
			String scriptReserva = "DELETE RESERVA WHERE ID="+t.getId();
			
			ArrayList<Cliente> clientes= t.getClientes();
			Iterator<Cliente> it = clientes.iterator();
			while(it.hasNext()){
				Cliente cliente = (Cliente)it.next();
				scriptTablasIntermedias = scriptTablasIntermedias + cliente.getId();
				if(it.hasNext())
				{
					scriptTablasIntermedias=scriptTablasIntermedias+", ";
				}
			}
			scriptTablasIntermedias = scriptTablasIntermedias + ");";
			return Base.ExecuteScript(scriptTablasIntermedias+scriptReserva);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer GetIdBase() {
		try {
			String query = "SELECT MAX(ID)+1 AS ID FROM RESERVA";
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
	
	public int ValidarDisponibilidad(Reserva r)
	{
		String query = "SELECT A.CAPACIDAD - (COUNT(RC.ID_CLIENTE)) AS 'CANTIDAD' FROM RESERVA AS R "+ 
						"INNER JOIN PAQUETE AS P ON P.ID = R.ID_PAQUETE "+
						"INNER JOIN VUELO AS V ON V.ID = P.ID_VUELO "+
						"INNER JOIN AVION AS A ON A.ID = V.ID_AVION "+
						"INNER JOIN reserva_cliente AS RC ON RC.ID_RESERVA = R.ID "+
						"WHERE R.ID_PAQUETE = " + r.getPaquete().getId();
		try
		{
			ResultSet rs = Base.ExecuteQuery(query);
			if(rs.next())
			{
				return rs.getInt("CANTIDAD");
			}
			return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}

}
