package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que crea un mensaje de tipo MListarUsuarios (Listado de Usuarios).
 * Hereda de la clase abstracta Mensaje
 */
public class MListarUsuarios extends Mensaje {

	private String passwordAdmin;
	
	//private Broker brokerListUsu;
	
	public MListarUsuarios() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * Constructor de la clase
     * @param passwordAdmin Contraseña del Administrador
     */
	public MListarUsuarios(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
		this.broker=new BrokerListUsu(this);
		// TODO Auto-generated constructor stub
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
     * Metodo que retortna la respuesta con la informacion de la base de datos
     * @return Respuesta Objeto tipo Respuesta
     */
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return broker.consultar();
	}

}
