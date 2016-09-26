package Mensajes;

public class MListarUsuarios extends Mensaje {

	private String passwordAdmin;
	
	public MListarUsuarios() {
		// TODO Auto-generated constructor stub
	}
	public MListarUsuarios(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
		// TODO Auto-generated constructor stub
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}

}
