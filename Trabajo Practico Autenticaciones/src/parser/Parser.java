package parser;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import respuesta.Estado;
import mensajes.Agregar;
import mensajes.Autenticar;
import mensajes.FactoryMensajes;
import mensajes.MListarAutenticaciones;
import mensajes.MListarUsuarios;
import mensajes.Mensaje;
import mensajes.Modificar;
import mensajes.Remover;
import respuesta.Respuesta;



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
	public Document analizaXml(Document doc,String host){
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
        	Respuesta respuesta= null;
        	for (int i = 0; i < cantNodos; i++) {
				switch (listaNodos.item(i).getNodeName()) {
				case "USERNAME":
					texto = listaNodos.item(i).getTextContent(); //recupera el contenido de la etiqueta
                    if(!texto.equals(""))
                    	usuario = texto;
                    else{
                    	respuesta=new Estado("ERROR","Nombre de Usuario vacio"); 
                    	return generarRespuesta(respuesta,tipo);
                    }
					break;
				case "PASSWORD":
                    texto = listaNodos.item(i).getTextContent();
                    if(!texto.equals(""))
                        password = texto;
                    else{
                    	respuesta=new Estado("ERROR","Contraseña vacia");
                    	return generarRespuesta(respuesta,tipo);
                    }
					break;
				case "ADM-PASS":
					texto = listaNodos.item(i).getTextContent();
					if(!texto.equals(""))
						passwordAdmin = texto;
                    else{
                    	respuesta=new Estado("ERROR","Contraseña administrador vacia");
                    	return generarRespuesta(respuesta,tipo);
                    } 
					break;
				case "NEW-PASS":
                    texto = listaNodos.item(i).getTextContent();
                    if(!texto.equals(""))
                    	passwordNuevo = texto;
                    else{
                    	respuesta=new Estado("ERROR","Contraseña nueva vacia");
                    	return generarRespuesta(respuesta,tipo);
                    }
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
        Mensaje mensaje = factoryMje.crearMensaje(tipo, usuario, password, passwordAdmin, passwordNuevo,host);
        
        //falta ver que hacer con el mensaje
		return generarRespuesta(mensaje.getRespuesta(),tipo);
	}
	
	public Document generarRespuesta(Respuesta respuesta,String tipo){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		switch (tipo) {
        case "LIST-USERS":
            //mensaje = new MListarUsuarios(passwordAdmin);
            break;
        case "LIST-AUT":
            //mensaje = new MListarAutenticaciones(passwordAdmin, usuario);
            break;
        default:
        	try {
        		Estado estado=(Estado) respuesta;
				doc=factory.newDocumentBuilder().newDocument();
				Element resEstado = doc.createElement("ACK");
				resEstado.setAttribute("STATUS", estado.getEstado());
				Element descripcion=doc.createElement("DESC");
				descripcion.setTextContent(estado.getDescripcion());
				
				resEstado.appendChild(descripcion);
				doc.appendChild(resEstado);
				
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return doc;
		}
		
		return null;
	}

}
