package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.w3c.dom.Document;

import parser.Parser;
import respuesta.Respuesta;

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
			
			if (!in.readLine().isEmpty()){
				String host = soc.getRemoteSocketAddress().toString();
				Parser parser = new Parser();
				Document docEnvia = parser.xmlToDoc(in.toString());
				Document docRecibe = parser.analizaXml(docEnvia,host);
				String respuestaXml = parser.docToXml(docRecibe);
				out = new PrintWriter(soc.getOutputStream(), true);
				out.println(respuestaXml);
			}
			
		
		}catch (IOException e) {
			//e.printStackTrace(); //cuando cierro el cliente da error de cliente reset capas que no va esta linea
			System.out.println("Fin conexión");
		}
	}//run()


	
	
	public Socket getSoc() {
		return soc;
	}

}
