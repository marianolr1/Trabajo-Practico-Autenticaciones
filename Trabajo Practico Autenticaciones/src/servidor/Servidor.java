package servidor;

import java.net.ServerSocket;
import java.net.Socket;

import conexionBD.Configuracion;

/**
 * Clase principal
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class Servidor {
	/**
     * Metodo principal de la aplicación, el cual inicializa el servidor
     * @param args 
     */
	public static void main(String[] args) {
		new Servidor().iniciar();
	}
	
	/**
     * Metodo encargado de crear el socket de conexion al servidor y de esperar peticiones entrantes
     */
	public void iniciar(){
		ServerSocket servidor = null;
		Socket socket = null;
		Configuracion configuracion = Configuracion.getInstance();
		
		try {
			int puerto =  Integer.parseInt(configuracion.getPropiedades().getProperty("socketport"));
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado - esperando conexiones");
			while (true) {
				socket = servidor.accept(); //espera de conexiones
				System.out.println("Conexión desde ip: "+socket.getInetAddress().toString().substring(1)); // ver de sacarle la "/" del principio de la ip
				
				new SocketServidor (socket); //se envia la conexión entrante para generacion de thread

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
