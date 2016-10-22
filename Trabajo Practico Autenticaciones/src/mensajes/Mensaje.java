package mensajes;

import respuesta.Respuesta;

/**
 * SuperClase abstracta para todos los tipos de mensajes.
 * @author Mariano Cortinez, Pablo Cassettai
 */
public abstract class Mensaje {

	public Mensaje() {
		// TODO Auto-generated constructor stub
	}
	/**
     * Metodo abstracto para obtener el objeto de tipo Respuesta
     * @return Respuesta Objeto de tipo Respuesta
     */
	public abstract Respuesta getRespuesta();
	

}
