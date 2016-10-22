package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.w3c.dom.Document;

import parser.Parser;

/**
 * Clase encargada de recibir peticiones y de leer los mensajes entrantes.
 * Hereda de la clase Thread
 */
public class SocketServidor extends Thread {
	
	private Socket soc;
	
	/**
     * Constructor de la clase
     * @param socket Objeto de tipo socket entrante
     */
	public SocketServidor (Socket socket) {
		soc = socket;
		start(); //ejecuta lo que esta en el metodo run()
	}
	
	
    /**
     * Metodo para leer un mensaje entrante, realizar las operaciones correspondientes, y luego enviar una respuesta de regreso
     * Redefine el metodo run() de la clase Thread
     */
	@Override
	public void run() {
		
		BufferedReader in;
		PrintWriter out;
               
		try {
			in = new BufferedReader(new InputStreamReader(soc.getInputStream())); ///recupero xml
			String mensajeEntrada=in.readLine();
			System.out.println("Mensaje recibido: "+mensajeEntrada);
			
			if (!mensajeEntrada.isEmpty()){
				String host = soc.getInetAddress().toString().substring(1);
				Parser parser = new Parser();
				Document docEnvia = parser.xmlToDoc(mensajeEntrada);
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
