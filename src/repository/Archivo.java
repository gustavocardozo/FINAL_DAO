package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import core.IArchivo;

public class Archivo<T> implements IArchivo<T> {

	protected String Path;
	protected Class<T> Clase;

	public Class<T> getClase() {
		return Clase;
	}

	public void setClase(Class<T> clase) {
		Clase = clase;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Archivo(Class<T> typeParameterClass) {
		this.Clase = typeParameterClass;
		this.setPath(this.getClase().getSimpleName() + ".obj");

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> ListadoArchivo() {

		ArrayList<T> registros = new ArrayList<T>();

		ObjectInputStream ois = null;
		try {

			File f = new File(this.getPath());
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			while (true) {
				T registro;
				registro = (T) ois.readObject();

				registros.add(registro);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return registros;
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T GetByIdArchivo(Integer Id) {
		ObjectInputStream ois = null;
		double idO = 0;
		T obj = null;
		try {

			File f = new File(this.getPath());
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while (true) {
				T objeto = (T) ois.readObject();
				Method getId = objeto.getClass().getMethod("getId");
				idO = (Integer) getId.invoke(objeto);
				if (Id == idO) {
					return objeto;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Boolean InsertarArchivo(T t) {

		ObjectOutputStream oos = null;

		try {

			File archivo = new File(this.getPath());
			if (archivo.exists() == true) {
				// en el constructor del FileOutputStream le paso el parametro
				// true, para que agregué objetos al final.
				oos = new ObjectOutputStream((new FileOutputStream(this.getPath(), true))) {
					// sobre-escribo el metodo que escribe el encabezado //del
					// archivo si no es el primer registro, lo cual hace que
					// pueda seguir //agregando objetos al final del archivo
					@Override
					protected void writeStreamHeader() throws IOException {
					}

				};

				oos.writeUnshared(t);
				return true;

			} else { // si el archivo no existe
				oos = new ObjectOutputStream((new FileOutputStream(this.getPath())));

				oos.writeObject(t);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Boolean ModificarArchivo(T t) {

		try {

			T obj = this.GetByIdArchivo((Integer) t.getClass().getMethod("getId").invoke(t));

			if (this.DeleteArchivo(obj)) {
				if (this.InsertarArchivo(t)) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean DeleteArchivo(T t) {

		try {

			Iterator<T> it = this.ListadoArchivo().iterator();

			while (it.hasNext()) {

				T obj = it.next();

				double objid = (Integer) obj.getClass().getMethod("getId").invoke(obj);
				double tid = (Integer) t.getClass().getMethod("getId").invoke(t);

				if (objid == tid) {
					it.remove();
					return true;
				}
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer GetID() {

		ObjectInputStream ois = null;
		Integer max = 0;

		try {

			File f = new File(this.getPath());
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				while (true) {
					T objeto = (T) ois.readObject();
					Method getId = objeto.getClass().getMethod("getId");
					Integer id = (Integer) getId.invoke(objeto);
					if (id > max) {
						max = id;
					}
				}
			} else {
				max = 0;
			}

			return max+1;

		} catch (Exception e) {
			e.printStackTrace();
			return max+1;

		} finally {
			try {
				ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return max+1;
			}
		}
	}
}
