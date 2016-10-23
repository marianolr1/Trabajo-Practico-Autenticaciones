package respuesta;

import java.util.LinkedList;

public class FactoryRespuesta {
	/**
     * Metodo para instanciar un objeto de tipo Respuesta
     * @param tipo String que identifica el tipo de respuesta
     * @param estado String Estado
     * @param desc String Descripcion
     * @param lautenticaciones Listado de autenticaciones
     * @param lusuarios listado de usuarios
     * @return Respuesta Objeto de tipo Respuesta
     */
	public Respuesta crearRespuesta(String tipo, String estado, String desc, LinkedList<Autenticacion> lautenticaciones,LinkedList<Usuario> lusuarios){
		Respuesta respuesta = null;
		
	       switch(tipo){
	            case "ESTADO":
	            	respuesta = new Estado(estado,desc);
	                break;
	            case "LIST-AUT":
	            	respuesta = new RListarAutenticaciones(lautenticaciones,desc);
	                break;
	            case "LIST-USERS":
	            	respuesta = new RListarUsuarios(lusuarios,desc);
	                break;
	            default:
	                System.err.println("Tipo de respuesta no reconocido");
	                break;
	        }
	      
	       return respuesta;
	}
}
