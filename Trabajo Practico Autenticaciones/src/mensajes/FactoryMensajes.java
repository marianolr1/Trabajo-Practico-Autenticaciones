package mensajes;
/**
 * Clase encargada de instanciar los objetos de tipo Mensaje
 */
public class FactoryMensajes {
	
	public FactoryMensajes() {
		// TODO Auto-generated constructor stub
	}
    /**
     * Metodo para instanciar un objeto de tipo Mensaje
     * @param tipo String que identifica el tipo de mensaje
     * @param usuario String Nombre de Usuario
     * @param password String Contraseña de Usuario
     * @param passwordAdmin String Contraseña de Administrador
     * @param passwordNuevo String Contraseña Nueva
     * @param host String Direccion Host del Cliente
     * @return Mensaje Objeto de tipo Mensaje
     */
	public Mensaje crearMensaje(String tipo, String usuario, String password, String passwordAdmin, String passwordNuevo,String host){
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
	                mensaje = new Autenticar(usuario,password,host);
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
