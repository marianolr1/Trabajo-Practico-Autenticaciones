package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que crea un mensaje de tipo Modificar.
 * Hereda de la clase abstracta Mensaje
 */
public class Modificar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordNuevo;
	//private Broker brokerModificar;
	
	public Modificar() {
		
	}
    /**
     * Constructor de la clase
     * @param usuario Nombre de Usuario
     * @param password Contrase�a de Usuario
     * @param passwordNuevo Nueva Contraseña
     */
	public Modificar(String usuario, String password, String passwordNuevo) {
		this.usuario=usuario;
		this.password=password;
		this.passwordNuevo=passwordNuevo;
		this.broker=new BrokerModificar(this);
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordNuevo() {
		return passwordNuevo;
	}
	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}
	/*
	public Broker getBrokerModificar() {
		return brokerModificar;
	}
	public void setBrokerModificar(Broker brokerModificar) {
		this.brokerModificar = brokerModificar;
	}*/
	/**
     * Metodo que retorna la respuesta con la informacion de la base de datos
     * @return Respuesta Objeto tipo Respuesta
     */
	@Override
	public Respuesta getRespuesta() {
		return broker.consultar();
	}

}
