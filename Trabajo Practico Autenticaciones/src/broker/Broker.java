package broker;
import respuesta.Respuesta;

public interface Broker {

	public abstract boolean claveCorrecta(String passAdmin);
	public abstract Respuesta consultar();
}
