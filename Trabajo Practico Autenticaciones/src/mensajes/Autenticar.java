package mensajes;

import broker.*;
import respuesta.Respuesta;

public class Autenticar extends Mensaje {
	
	private String usuario;
	private String password;
	private String host;
	private Broker brokerAutenticar;
	
	
	public Autenticar(String usuario, String password) {
		this.usuario=usuario;
		this.password=password;
		this.brokerAutenticar=new BrokerAutenticar(this);
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
	public Broker getBrokerAutenticar() {
		return brokerAutenticar;
	}
	public void setBrokerAutenticar(Broker brokerAutenticar) {
		this.brokerAutenticar = brokerAutenticar;
	}
	@Override
	public Respuesta getRespuesta() {
		
		return brokerAutenticar.consultar();
	}

}
