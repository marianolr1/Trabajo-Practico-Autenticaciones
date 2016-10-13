package mensajes;

import broker.*;
import respuesta.Respuesta;

public class Modificar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordNuevo;
	private Broker brokerModificar;
	
	public Modificar() {
		// TODO Auto-generated constructor stub
	}
	public Modificar(String usuario, String password, String passwordNuevo) {
		this.usuario=usuario;
		this.password=password;
		this.passwordNuevo=passwordNuevo;
		this.brokerModificar=new BrokerModificar(this);
		// TODO Auto-generated constructor stub
	}
	public void modificarUsuario(String usuario,String password,String passwordNuevo){
		
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
	public Broker getBrokerModificar() {
		return brokerModificar;
	}
	public void setBrokerModificar(Broker brokerModificar) {
		this.brokerModificar = brokerModificar;
	}
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return brokerModificar.consultar();
	}

}
