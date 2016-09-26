package Mensajes;

public class Autenticar extends Mensaje {
	
	private String usuario;
	private String password;
	
	
	public Autenticar(String usuario, String password) {
		this.usuario=usuario;
		this.password=password;
		// TODO Auto-generated constructor stub
	}
	public void autenticarUsuario(String usuario,String password){
		
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

}
