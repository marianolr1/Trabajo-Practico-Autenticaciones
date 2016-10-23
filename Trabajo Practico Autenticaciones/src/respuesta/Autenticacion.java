package respuesta;

import java.time.LocalDate;
/**
 * Clase que contiene la información de una Autenticación.
 * Utilizada para generar una respuesta al mensaje MListarAutenticaciones
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class Autenticacion {
	/**
     * Direccion Host del Cliente
     */
	private String host;
	/**
     * Fecha y Horario de la Autenticación
     */
	private LocalDate timeStamp;
    
	/**
     * Constructor de la clase
     * @param host Direccion Host del Cliente
     * @param timestamp Fecha y Hora de la Autenticación
     */
	public Autenticacion(String host,LocalDate timestamp) {
		this.host=host;
		this.timeStamp=timestamp;
	}
	

	/**
	 * Setters/Getters
	 */
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
}
