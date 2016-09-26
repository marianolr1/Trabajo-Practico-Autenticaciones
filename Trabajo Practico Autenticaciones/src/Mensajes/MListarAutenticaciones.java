package Mensajes;

public class MListarAutenticaciones extends Mensaje {

	private String usuario;
	private String passwordAdmin;
	
	public MListarAutenticaciones() {
		// TODO Auto-generated constructor stub
	}
	public MListarAutenticaciones(String passwordAdmin, String usuario) {
		this.passwordAdmin = passwordAdmin;
		this.usuario = usuario;
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
}
