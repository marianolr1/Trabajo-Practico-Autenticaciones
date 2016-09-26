package Respuesta;

import java.time.LocalDate;

public class Autenticacion {

	//private String nombreUsuario;
	private String host;
	private LocalDate timeStamp;
	
	public Autenticacion() {
		// TODO Auto-generated constructor stub
	}
//Region - Setters/Getters
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
//EndRegion
}
