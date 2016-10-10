package Broker;
import Respuesta.Respuesta;

public interface Broker {

	public abstract boolean claveCorrecta(String passAdmin);
	public abstract Respuesta consultar();
}
