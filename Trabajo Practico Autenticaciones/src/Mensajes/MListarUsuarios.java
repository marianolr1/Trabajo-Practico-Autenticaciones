package Mensajes;

import Broker.*;
import Respuesta.Respuesta;

public class MListarUsuarios extends Mensaje {

	private String passwordAdmin;
	private Broker brokerListUsu;
	
	public MListarUsuarios() {
		// TODO Auto-generated constructor stub
	}
	public MListarUsuarios(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
		this.brokerListUsu=new BrokerListUsu(this);
		// TODO Auto-generated constructor stub
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	public Broker getBrokerListUsu() {
		return brokerListUsu;
	}
	public void setBrokerListUsu(Broker brokerListUsu) {
		this.brokerListUsu = brokerListUsu;
	}
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return brokerListUsu.consultar();
	}

}
