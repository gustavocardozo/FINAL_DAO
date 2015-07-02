package core;

import java.util.ArrayList;

public interface IArchivo<T> {
	
	ArrayList<T> ListadoArchivo();

	T GetByIdArchivo(Integer Id);

	Boolean InsertarArchivo(T t);

	Boolean ModificarArchivo(T t);

	Boolean DeleteArchivo(T t);
	
	Integer GetID();

}
