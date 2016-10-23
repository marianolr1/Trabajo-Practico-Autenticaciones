package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.Modificar;
import respuesta.Autenticacion;
import respuesta.Estado;
import respuesta.FactoryRespuesta;
import respuesta.Respuesta;
import respuesta.Usuario;
/**
 * Clase que define al objeto Broker del mensaje Modificar.
 * Constituye la creacion y ejecucion de la consulta a la base de datos.
 * Implementa a la interfaz Broker
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class BrokerModificar implements Broker {
	
	private String consulta="";
	private Modificar mensaje=null;
	private Conexion conexion;

    /**
     * Constructor de la clase
     * @param mensaje Objeto Mensaje de tipo Modificar
     */
	public BrokerModificar(Modificar mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(mensaje.getPassword())) {
			this.consulta="update usuarios "
					+ "set password=? where username=? and password=?";
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
				statement.setString(1,mensaje.getPasswordNuevo());
				statement.setString(2,mensaje.getUsuario());
				statement.setString(3,mensaje.getPassword());
				rs=statement.executeUpdate();
				conexion.getConexion().setAutoCommit(true);
				if (rs!=0) {
					estado="OK";
					desc="Clave modificada con exito";
				}	
			}else {
				estado="ERROR";
				desc="Usuario o clave incorrecta";
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
	 * Metodo para verificar si el password es correcto
	 * @return retorna un boolean indicando si esta correcta o no la misma
	 */
	@Override
	public boolean claveCorrecta(String passAdmin) {
		String consulta="select password from usuarios where username=?";
		String pass="";
		ResultSet rs;
		try {
			conexion.getConexion().setAutoCommit(false);
			
			PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
			statement.setString(1,mensaje.getUsuario());
			rs=statement.executeQuery();
			if (!rs.next()){
                System.out.println("no hay registros");
			}
			pass=rs.getString(1);
			
			
			conexion.getConexion().setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
