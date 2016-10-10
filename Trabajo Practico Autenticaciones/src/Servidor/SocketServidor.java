package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketServidor extends Thread {
	
	private Socket soc;
	
	public SocketServidor (Socket socket) {
		soc = socket;
		start(); //ejecuta lo que esta en el metodo run()
	}

	@Override
	public void run() {
		
		BufferedReader in;
		PrintWriter out;
               
		try {
			in = new BufferedReader(new InputStreamReader(soc.getInputStream())); ///recupero xml
			System.out.println("Mensaje recibido: "+in.readLine());
			
			//este bloque muestra todas las lineas del mensaje enviado, si es que no lo mandan en una sola linea, preguntar como se van a enviar los mjes
			/*String linea = in.readLine();
			while(linea!=null){
				System.out.println(linea);
				linea=in.readLine();		
				}*/
			
			out = new PrintWriter(soc.getOutputStream(), true);
			out.println("Hola Estimado cliente");// va el xml de salida
		
		}catch (IOException e) {
			//e.printStackTrace(); //cuando cierro el cliente da error de cliente reset capas que no va esta linea
			System.out.println("Fin conexión");
		}
	}//run()


	
	
	public Socket getSoc() {
		return soc;
	}

}
