package parser;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import Mensajes.FactoryMensajes;
import Mensajes.Mensaje;
import Respuesta.Estado;
import Respuesta.Respuesta;


public class Parser {

	
	// convierte un xml que vienen en un string en tipo document para poder acceder a sus etiquetas
	public Document xmlToDoc(String xml){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build;
		Document doc = null;
		
		try {
			build = factory.newDocumentBuilder();
			doc = build.parse(new InputSource(new StringReader(xml)));
			return doc;
		} catch (Exception e) {
			System.out.println("La cadena no tiene un formato xml valido");
			e.printStackTrace();
			return null;
		}
	}
	
	// transforma un xml en formato document a uno en formato string
	public String docToXml(Document doc){
		StringWriter sw = new StringWriter();
		try {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(sw));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return sw.toString();
	}
	// recupero todos los datos necesarios para crear un mensaje del documento xml
	public Respuesta analizaXml(Document doc){
		String tipo = null;
        String usuario = null;
        String password = null;
        String passwordAdmin = null;
        String passwordNuevo = null;
        String texto;
        FactoryMensajes factoryMje;
        
        try{
        	tipo = doc.getDocumentElement().getAttribute("TYPE");
        	NodeList listaNodos = doc.getDocumentElement().getChildNodes(); //reculero todos los nodos(etiquetas)
        	int cantNodos = listaNodos.getLength(); //cantidad de nodos

        	for (int i = 0; i < cantNodos; i++) {
				switch (listaNodos.item(i).getNodeName()) {
				case "USERNAME":
					texto = listaNodos.item(i).getTextContent(); //recupera el contenido de la etiqueta
                    if(!texto.equals(""))
                    	usuario = texto;
                    else
                    	return new Estado("ERROR","Nombre de Usuario vacio"); 
					break;
				case "PASSWORD":
                    texto = listaNodos.item(i).getTextContent();
                    if(!texto.equals(""))
                        password = texto;
                    else
                    	return new Estado("ERROR","Contraseña vacia");
					break;
				case "ADM-PASS":
					passwordAdmin = listaNodos.item(i).getTextContent();
					break;
				case "NEW-PASS":
                    texto = listaNodos.item(i).getTextContent();
                    if(!texto.equals(""))
                    	passwordNuevo = texto;
	
					break;
				default:
					System.err.println("Etiqueta erronea o desconocida");
					break;
				}
			}		
        			
        }catch(Exception e){
        	e.printStackTrace();
        }
        factoryMje = new FactoryMensajes();
        Mensaje mensaje = factoryMje.crearMensaje(tipo, usuario, password, passwordAdmin, passwordNuevo);
        
        //falta ver que hacer con el mensaje
		return mensaje.getRespuesta();
	}

}
