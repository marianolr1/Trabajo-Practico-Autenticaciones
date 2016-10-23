package mensajes;

import broker.*;
import respuesta.Respuesta;

/**
 * Clase que crea el mensaje de tipo Autenticar.
 * Hereda de la clase abstracta Mensaje
 */
public class Autenticar extends Mensaje {
	
	private String usuario;
	private String password;
	private String host;
	
	
	//private Broker brokerAutenticar;
	
    /**
     * Constructor de la clase
     * @param usuario Nombre de Usuario
     * @param password Contraseña de Usuario
     * @param host Direccion de host del cliente
     */
	public Autenticar(String usuario, String password,String host) {
		this.usuario=usuario;
		this.password=password;
		this.broker=new BrokerAutenticar(this);
		this.host=host;
		// TODO Auto-generated constructor stub
	}
	public void autenticarUsuario(String usuario,String password){
		
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
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	/*
	public Broker getBrokerAutenticar() {
		return brokerAutenticar;
	}
	public void setBrokerAutenticar(Broker brokerAutenticar) {
		this.brokerAutenticar = brokerAutenticar;
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
