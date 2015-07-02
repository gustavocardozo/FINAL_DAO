package core;
import java.util.ArrayList;

public interface IBase<T> {

	ArrayList<T> ListadoBase();

	T GetByIdBase(Integer id);

	Boolean InsertarBase(T t);

	Boolean ModificarBase(T t);

	Boolean DeleteBase(T t);
	
	Integer GetIdBase();

}
