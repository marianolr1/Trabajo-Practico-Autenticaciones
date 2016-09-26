package Mensajes;

public class Remover extends Mensaje {
	private String usuario;
	private String passwordAdmin;
	
	
	public Remover(String usuario, String passwordAdmin) {
		this.usuario=usuario;
		this.passwordAdmin=passwordAdmin;
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

}
