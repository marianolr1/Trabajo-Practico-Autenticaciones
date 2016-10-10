package Mensajes;

import Broker.*;

public class Agregar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordAdmin;
	private Broker brokerAgregar;
	

	public Agregar(String usuario, String password, String passwordAdmin) {
		this.usuario=usuario;
		this.password=password;
		this.passwordAdmin=passwordAdmin;
		this.brokerAgregar=new BrokerAgregar(this);
	}
	/*public void agregarUsuario(String usuario,String password,String passwordadmin){
		
	}*/
//Region - Setters 
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
	
}
