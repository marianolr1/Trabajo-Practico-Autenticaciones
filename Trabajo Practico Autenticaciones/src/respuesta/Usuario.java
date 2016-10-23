package respuesta;

import java.time.LocalDate;
/**
 * Clase que contiene la informacion de un Usuario.
 * Utilizada para generar una respuesta al mensaje List-Users (Listado de Usuarios)
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class Usuario {

	private String nombreUsuario;
	private LocalDate timeStamp;
    
	/**
     * Constructor de la clase
     * @param nombreUsuario String Nombre de Usuario
     * @param timestamp String Fecha y Horario de alta
     */
	public Usuario(String nombreUsuario, LocalDate timeStamp) {
		this.nombreUsuario=nombreUsuario;
		this.timeStamp=timeStamp;
	}

/**
 * Setters/Getters
 */
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
}
