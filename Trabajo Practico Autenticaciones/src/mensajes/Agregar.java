package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que constituye el dominio del mensaje de tipo Add.
 * Hereda de la clase abstracta Mensaje
 */
public class Agregar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordAdmin;
	private Broker brokerAgregar;
	
    /**
     * Constructor de la clase
     * @param username Nombre de Usuario
     * @param password Contraseña de Usuario
     * @param passwordAdmin Contraseña de Administrador
     */
	public Agregar(String usuario, String password, String passwordAdmin) {
		this.usuario=usuario;
		this.password=password;
		this.passwordAdmin=passwordAdmin;
		this.brokerAgregar=new BrokerAgregar(this);
	}

//Region - Setters 
	
    /**
     * Metodos Get y Set
     */
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

	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	public Broker getBrokerAgregar() {
		return brokerAgregar;
	}
	public void setBrokerAgregar(Broker brokerAgregar) {
		this.brokerAgregar = brokerAgregar;
	}
//EndRegion
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return brokerAgregar.consultar();
	}
	
}
