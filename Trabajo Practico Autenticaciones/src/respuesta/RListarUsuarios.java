package respuesta;

import java.util.LinkedList;

/**
 * Clase que define la respuesta de tipo ListaUsuario, devolviendo una lista de todos los usuarios.
 * Utilizada para el mensaje de tipo List-Users en caso que no haya ocurrido un error.
 * @author Julian, Luciano, Guillermo
 */
public class RListarUsuarios extends Respuesta {

	private LinkedList<Usuario> usuarios;
	private String respuesta;
	
	public RListarUsuarios(LinkedList<Usuario> lista,String respuesta) {
		this.usuarios=lista;
		this.respuesta=respuesta;
	}

	/**
	 * Setters/Getters
	 */
	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(LinkedList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
