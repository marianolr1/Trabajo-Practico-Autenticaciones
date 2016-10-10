package Respuesta;

import java.util.LinkedList;

public class RListarUsuarios extends Respuesta {

	private LinkedList<Usuario> usuarios;
	private String respuesta;
	
	public RListarUsuarios(LinkedList<Usuario> lista,String respuesta) {
		this.usuarios=lista;
		this.respuesta=respuesta;
	}
//Region - Setters/Getters
	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(LinkedList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
//EndRegion
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
