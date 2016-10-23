package mensajes;

import broker.*;
import respuesta.Respuesta;
/**
 * Clase que crea un mensaje de tipo List-Aut (Listado de Autenticaciones).
 * Hereda de la clase abstracta Mensaje
 */
public class MListarAutenticaciones extends Mensaje {

	private String usuario;
	private String passwordAdmin;
	
	//private Broker brokerListAut;
	
	public MListarAutenticaciones() {
		// TODO Auto-generated constructor stub
	}
    /**
     * Constructor de la clase
     * @param passwordAdmin Contraseña de Administrador
     * @param usuario Nombre de Usuario
     */
	public MListarAutenticaciones(String passwordAdmin, String usuario) {
		this.passwordAdmin = passwordAdmin;
		this.usuario = usuario;
		this.broker=new BrokerListAut(this);
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
	public Broker getBrokerListAut() {
		return brokerListAut;
	}
	public void setBrokerListAut(Broker brokerListAut) {
		this.brokerListAut = brokerListAut;
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
