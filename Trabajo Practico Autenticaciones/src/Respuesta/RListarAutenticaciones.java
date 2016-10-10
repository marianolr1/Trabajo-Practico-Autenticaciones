package Respuesta;

import java.util.LinkedList;

public class RListarAutenticaciones extends Respuesta {

	private LinkedList<Autenticacion> autenticaciones;
	private String respuesta;
	
	public RListarAutenticaciones(LinkedList<Autenticacion> lista,String respuesta) {
		this.autenticaciones=lista;
		this.respuesta=respuesta;
	}
//Region - Setters/Getters
	public LinkedList<Autenticacion> getAutenticaciones() {
		return autenticaciones;
	}

	public void setAutenticaciones(LinkedList<Autenticacion> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}
//EndRegion
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
