package mensajes;

import broker.Broker;
import respuesta.Respuesta;

/**
 * SuperClase abstracta para todos los tipos de mensajes.
 * @author Mariano Cortinez, Pablo Cassettai
 */
public abstract class Mensaje {
	
	//AGREGADO 23/10
	protected Broker broker;
	
	public Mensaje() {
		// TODO Auto-generated constructor stub
	}
	/**
     * Metodo abstracto para obtener el objeto de tipo Respuesta
     * @return Respuesta Objeto de tipo Respuesta
     */
	public abstract Respuesta getRespuesta();
	public Broker getBroker() {
		return broker;
	}
	public void setBroker(Broker broker) {
		this.broker = broker;
	}
	

}
