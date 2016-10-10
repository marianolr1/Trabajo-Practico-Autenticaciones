package Respuesta;

import java.time.LocalDate;

public class Usuario {

	private String nombreUsuario;
	private LocalDate timeStamp;
	
	public Usuario(String nombreUsuario, LocalDate timeStamp) {
		this.nombreUsuario=nombreUsuario;
		this.timeStamp=timeStamp;
	}

//Region - Setters/Getters
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timeStamp = timestamp;
	}
//EndRegion
}
