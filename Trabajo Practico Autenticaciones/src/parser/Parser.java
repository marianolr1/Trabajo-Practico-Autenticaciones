package parser;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;

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
import respuesta.*;
import mensajes.Agregar;
import mensajes.Autenticar;
import mensajes.FactoryMensajes;
import mensajes.MListarAutenticaciones;
import mensajes.MListarUsuarios;
import mensajes.Mensaje;
import mensajes.Modificar;
import mensajes.Remover;
import respuesta.Respuesta;


/**
 * Clase encargada de analizar y parsear un documento XML, convirtiendolo a texto o viceversa
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class Parser {

	/**
     * Metodo para convertir un xml que vienen en un string a Document
     * @param xml String a convertir
     * @return Document Objeto de tipo document
     */
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
	
	/**
     * Metodo para convertir un Document a String
     * @param doc Objeto de tipo Document
     * @return String de conversion
     */
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
	
	/**
     * Metodo para analizar un documento XML, crear un objeto de tipo Mensaje 
     * @param doc Objeto de tipo Document
     * @param host Direccion Host del Cliente
     * @return Document de tipo Document, para informar al cliente el resultado de la operacion
     */
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
        
		return generarRespuesta(mensaje.getRespuesta(),tipo);
	}
	/**
     * Metodo para transformar una respuesta en tipo Document 
     * @param respuesta Objeto de tipo Respuesta
     * @param tipo de la respuesta
     * @return Document de tipo Document
     */
	public Document generarRespuesta(Respuesta respuesta,String tipo){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		switch (tipo) {
        case "LIST-USERS":
        	try {
        		RListarUsuarios resListUsu=(RListarUsuarios) respuesta;
				doc=factory.newDocumentBuilder().newDocument();
				Element element = doc.createElement("LIST-USERS");
				LinkedList<Usuario> lista=resListUsu.getUsuarios();
				if (!lista.isEmpty()) {
					 for(Usuario user : lista){
			                Element usuario = doc.createElement("USER");
			                Element username = doc.createElement("USERNAME");
			                username.setTextContent(user.getNombreUsuario());
			                Element timestamp = doc.createElement("TIMESTAMP");
			                timestamp.setTextContent(user.getTimeStamp().toString());
			                usuario.appendChild(username);
			                usuario.appendChild(timestamp);
			                element.appendChild(usuario);                
			         }
				}else {
					doc=respuestaEstado("ERROR",resListUsu.getRespuesta());
					return doc;
				}
				doc.appendChild(element);
				
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return doc;
            
        case "LIST-AUT":
            try {
            	RListarAutenticaciones resListAut=(RListarAutenticaciones) respuesta;
				doc=factory.newDocumentBuilder().newDocument();
				Element element = doc.createElement("LIST-USERS");
				LinkedList<Autenticacion> lista=resListAut.getAutenticaciones();
				if (!lista.isEmpty()) {
					 for(Autenticacion aut : lista){
			                Element autenticacion = doc.createElement("AUT");
			                Element host = doc.createElement("HOST");
			                host.setTextContent(aut.getHost());
			                Element timestamp = doc.createElement("TIMESTAMP");
			                timestamp.setTextContent(aut.getTimeStamp().toString());
			                autenticacion.appendChild(host);
			                autenticacion.appendChild(timestamp);
			                element.appendChild(autenticacion);                
			         }
				}else {
					doc=respuestaEstado("ERROR",resListAut.getRespuesta());
					return doc;
				}
				doc.appendChild(element); 
			} catch (Exception e) {
				// TODO: handle exception
			}
            return doc;
        default:
        	
        	Estado estado=(Estado) respuesta;
			doc=respuestaEstado(estado.getEstado(),estado.getDescripcion());	
			
        	return doc;
		}
		
	}
	
	/**
     * Metodo para crear respuesta tipo estado 
     * @param est Estado 
     * @param desc Descripcion de estado
     * @return Document de tipo Document
     */
	public Document respuestaEstado(String est,String desc){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		Estado estado=new Estado(est,desc);
		try {
    		
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

}
