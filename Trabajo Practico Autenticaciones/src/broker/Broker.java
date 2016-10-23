package broker;
import respuesta.Respuesta;
/**
 * Interfaz para definir los objetos de tipo Broker de cada mensaje
 *  @author Mariano Cortinez, Pablo Cassettai
 */
public interface Broker {
	/**
     * Metodo abstracto que realiza la verificacion de la clave (del admin en el ABM y listado, del usuario en autenticar)
     * Debe ser definida en cada clase Broker de cada uno de los mensajes
     * @return Un objeto de tipo boolean, indica si es correcta o no
     */
	public abstract boolean claveCorrecta(String passAdmin);
	/**
     * Metodo abstracto que realiza la consulta a la base de datos.
     * Debe ser definida en cada clase Broker de cada uno de los mensajes
     * @return Un objeto de tipo Respuesta, con la informacion necesaria para notificar al cliente el resultado de la operacion
     */
	public abstract Respuesta consultar();
}
