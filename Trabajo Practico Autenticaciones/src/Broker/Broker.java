package Broker;
import Respuesta.Respuesta;;

public abstract class Broker {

	protected String consulta;
	public Broker() {
		// TODO Auto-generated constructor stub
	}

	public abstract Respuesta consultar();
}
