package servidor;

import java.net.ServerSocket;
import java.net.Socket;

import conexionBD.Configuracion;


public class Servidor {

	public static void main(String[] args) {
		new Servidor().iniciar();
	}

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
