package Respuesta;

import java.util.LinkedList;

public class RListarUsuarios extends Respuesta {

	private LinkedList<Usuario> usuarios;
	public RListarUsuarios() {
		// TODO Auto-generated constructor stub
	}
//Region - Setters/Getters
	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(LinkedList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
//EndRegion
}
