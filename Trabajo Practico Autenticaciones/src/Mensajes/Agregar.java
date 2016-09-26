package Mensajes;

public class Agregar extends Mensaje {
	private String usuario;
	private String password;
	private String passwordAdmin;
	

	public Agregar(String usuario, String password, String passwordAdmin) {
		this.usuario=usuario;
		this.password=password;
		this.passwordAdmin=passwordAdmin;
		// TODO Auto-generated constructor stub
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
//EndRegion
}
