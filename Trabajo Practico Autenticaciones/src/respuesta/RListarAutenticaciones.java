package respuesta;

import java.util.LinkedList;
/**
 * Clase que define la respuesta de tipo ListaAutenticacion, devolviendo la lista de autenticaciones de un usuario.
 * Utilizada para el mensaje de tipo List-Aut en caso que no haya ocurrido un error.
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class RListarAutenticaciones extends Respuesta {

	private LinkedList<Autenticacion> autenticaciones;
	private String respuesta;
	
    /**
     * Constructor de la clase
     * @param lista Lista de Autenticaciones
     * @param desc Descripcion de la respuesta
     */
	public RListarAutenticaciones(LinkedList<Autenticacion> lista,String respuesta) {
		this.autenticaciones=lista;
		this.respuesta=respuesta;
	}

	/**
	 * Setters/Getters
	 */
	
	public LinkedList<Autenticacion> getAutenticaciones() {
		return autenticaciones;
	}

	public void setAutenticaciones(LinkedList<Autenticacion> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}

	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
