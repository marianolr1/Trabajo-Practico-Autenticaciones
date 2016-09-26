package Respuesta;

import java.util.LinkedList;

public class RListarAutenticaciones extends Respuesta {

	private LinkedList<Autenticacion> autenticaciones;
	
	public RListarAutenticaciones() {
		// TODO Auto-generated constructor stub
	}
//Region - Setters/Getters
	public LinkedList<Autenticacion> getAutenticaciones() {
		return autenticaciones;
	}

	public void setAutenticaciones(LinkedList<Autenticacion> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}
//EndRegion
}
