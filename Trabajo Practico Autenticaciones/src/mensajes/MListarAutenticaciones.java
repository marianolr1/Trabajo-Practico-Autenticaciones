package mensajes;

import broker.*;
import respuesta.Respuesta;

public class MListarAutenticaciones extends Mensaje {

	private String usuario;
	private String passwordAdmin;
	private Broker brokerListAut;
	
	public MListarAutenticaciones() {
		// TODO Auto-generated constructor stub
	}
	public MListarAutenticaciones(String passwordAdmin, String usuario) {
		this.passwordAdmin = passwordAdmin;
		this.usuario = usuario;
		this.brokerListAut=new BrokerListAut(this);
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
	
	public Broker getBrokerListAut() {
		return brokerListAut;
	}
	public void setBrokerListAut(Broker brokerListAut) {
		this.brokerListAut = brokerListAut;
	}
	
	@Override
	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return brokerListAut.consultar();
	}
}
