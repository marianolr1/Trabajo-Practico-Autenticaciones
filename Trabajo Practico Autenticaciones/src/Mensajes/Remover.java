package Mensajes;

import Broker.*;
import Respuesta.Respuesta;

public class Remover extends Mensaje {
	private String usuario;
	private String passwordAdmin;
	private Broker brokerRemover;
	
	
	public Remover(String usuario, String passwordAdmin) {
		this.usuario=usuario;
		this.passwordAdmin=passwordAdmin;
		this.brokerRemover=new BrokerRemover(this);
		// TODO Auto-generated constructor stub
	}
	/*public void removerUsuario(String usuario,String passwordAdmin){
		
	}*/
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
	public Broker getBrokerRemover() {
		return brokerRemover;
	}
	public void setBrokerRemover(Broker brokerRemover) {
		this.brokerRemover = brokerRemover;
	}
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return brokerRemover.consultar();
	}

}
