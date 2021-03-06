package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que crea un mensaje de tipo List-Users (Listado de Usuarios).
 * Hereda de la clase abstracta Mensaje
 */
public class MListarUsuarios extends Mensaje {

	private String passwordAdmin;
	
	//private Broker brokerListUsu;
	
	public MListarUsuarios() {
		
	}
	 /**
     * Constructor de la clase
     * @param passwordAdmin Contraseņa del Administrador
     */
	public MListarUsuarios(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
		this.broker=new BrokerListUsu(this);
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	/*
	public Broker getBrokerListUsu() {
		return brokerListUsu;
	}
	public void setBrokerListUsu(Broker brokerListUsu) {
		this.brokerListUsu = brokerListUsu;
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
