package mensajes;

public class FactoryMensajes {
	
	public FactoryMensajes() {
		// TODO Auto-generated constructor stub
	}
	
	public Mensaje crearMensaje(String tipo, String usuario, String password, String passwordAdmin, String passwordNuevo){
		Mensaje mensaje = null;
	       
	       switch(tipo){
	            case "ADD":
	                mensaje = new Agregar(usuario,password,passwordAdmin);
	                break;
	            case "REMOVE":
	                mensaje = new Remover(usuario,passwordAdmin);
	                break;
	            case "MODIFY":
	                mensaje = new Modificar(usuario,password,passwordNuevo);
	                break;
	            case "AUTHENTICATE":
	                mensaje = new Autenticar(usuario,password);
	                break;
	            case "LIST-USERS":
	                mensaje = new MListarUsuarios(passwordAdmin);
	                break;
	            case "LIST-AUT":
	                mensaje = new MListarAutenticaciones(passwordAdmin, usuario);
	                break;
	            default:
	                System.err.println("Tipo de mensaje no reconocido");
	                break;
	        }
	      
	       return mensaje;
	}
}
