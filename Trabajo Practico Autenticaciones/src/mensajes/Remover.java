package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que crea un mensaje de tipo Remover.
 * Hereda de la clase abstracta Mensaje
 */
public class Remover extends Mensaje {
	private String usuario;
	private String passwordAdmin;
	
	//private Broker brokerRemover;
	
    /**
     * Constructor de la clase
     * @param usuario Nombre de Usuario
     * @param passwordAdmin Contraseña de Administrador
     */
	public Remover(String usuario, String passwordAdmin) {
		this.usuario=usuario;
		this.passwordAdmin=passwordAdmin;
		this.broker=new BrokerRemover(this);
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	/*
	public Broker getBrokerRemover() {
		return brokerRemover;
	}
	public void setBrokerRemover(Broker brokerRemover) {
		this.brokerRemover = brokerRemover;
	}*/
	/**
     * Metodo que retortna la respuesta con la informacion de la base de datos
     * @return Respuesta Objeto tipo Respuesta
     */
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return broker.consultar();
	}

}
