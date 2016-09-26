package Mensajes;

public class Modificar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordNuevo;
	
	public Modificar() {
		// TODO Auto-generated constructor stub
	}
	public Modificar(String usuario, String password, String passwordNuevo) {
		this.usuario=usuario;
		this.password=password;
		this.passwordNuevo=passwordNuevo;
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

}
