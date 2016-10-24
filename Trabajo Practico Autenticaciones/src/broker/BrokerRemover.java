package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.Autenticacion;
import respuesta.FactoryRespuesta;
import respuesta.Respuesta;
import respuesta.Usuario;
/**
 * Clase que define al objeto Broker del mensaje Remover.
 * Constituye la creacion y ejecucion de la consulta a la base de datos.
 * Implementa a la interfaz Broker
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class BrokerRemover implements Broker {
	
	private String consulta="";
	private Remover mensaje=null;
	private Conexion conexion;

    /**
     * Constructor de la clase
     * @param mensaje Objeto Mensaje de tipo Remover
     */
	public BrokerRemover(Remover mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(mensaje.getPasswordAdmin())) {
			this.consulta="delete from usuarios "
					+ "where username=?";
		}
	}

    /**
     * Metodo que realiza la consulta a la base de datos.
     * Define el metodo consultar() de la interfaz Broker
     * @return Un objeto de tipo Respuesta, con la informacion necesaria para notificar al cliente el resultado de la operacion
     */
	@Override
	public synchronized Respuesta consultar() {
		Respuesta respuesta=null;
		String desc="";
		String estado="ERROR";
		int rs=0;
		FactoryRespuesta factoryRta = new FactoryRespuesta();
		
		try {
			if (this.consulta!="") {
				conexion.getConexion().setAutoCommit(false);
				PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
				statement.setString(1,mensaje.getUsuario());
				rs=statement.executeUpdate();
				conexion.getConexion().setAutoCommit(true);
				if (rs!=0) {
					estado="OK";
					desc="Usuario eliminado";
				}	
			}else {
				estado="ERROR";
				desc="Clave incorrecta";
			}
			} catch (Exception e) {
				estado="ERROR";
				desc="Error de conexion";
		}
		//respuesta=new Estado(estado,desc);
		
		LinkedList<Autenticacion> lautenticaciones = null; 	// parametro no utilizado
		LinkedList<Usuario> lusuarios  = null;				// parametro no utilizado
		respuesta= factoryRta.crearRespuesta("ESTADO", estado, desc, lautenticaciones, lusuarios);
		
		return respuesta;
	}

	/**
	 * Metodo para verificar el password del administrador
	 * @return retorna un boolean indicando si esta correcta o no la misma
	 */
	@Override
	public boolean claveCorrecta(String passAdmin) {
		String consulta="select password from usuarios where isadmin=1";
		String pass="";
		ResultSet rs;
		try {
			conexion.getConexion().setAutoCommit(false);
			
			PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
			rs=statement.executeQuery();
			if (!rs.next()){
                System.out.println("no hay registros");
			}
			pass=rs.getString(1);
			conexion.getConexion().setAutoCommit(true);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
