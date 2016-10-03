package Servidor;

import javax.swing.text.Document;
import javax.xml.transform.Transformer;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public void prueba(){		
        }
	public static void main(String[] args) {
		new Servidor().iniciar();
	}

	public void iniciar(){
		ServerSocket servidor = null;
		Socket socket = null;
		
		try {
			servidor = new ServerSocket(5050); //el puerto lo tenemos que levantar del archivo de configuraciones?
			System.out.println("Servidor iniciado - esperando conexiones");
			while (true) {
				socket = servidor.accept(); //espera de conexiones
				System.out.println("Conexión desde ip: "+socket.getInetAddress()); // ver de sacarle la "/" del principio de la ip
				
				new SocketServidor (socket); //se envia la conexión entrante para generacion de thread

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
