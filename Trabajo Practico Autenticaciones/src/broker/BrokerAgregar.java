package broker;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;

import java.sql.*;
import java.util.LinkedList;
/**
 * Clase que define al objeto Broker del mensaje Agregar.
 * Constituye la creacion y ejecucion de la consulta a la base de datos.
 * Implementa a la interfaz Broker
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class BrokerAgregar implements Broker {
	private String consulta="";
	private Agregar mensaje=null;
	private Conexion conexion;
    
	/**
     * Constructor de la clase
     * @param mensaje Objeto Mensaje de tipo Agregar
     */
	public BrokerAgregar(Agregar mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		boolean tru=claveCorrecta(mensaje.getPasswordAdmin());
		if (tru) {
			this.consulta="insert into usuarios (`username`, `password`,`timestamp`, `isadmin`) values(?,?,now(),0)";
		}
		
	}
    /**
     * Metodo para guardar la respuesta en un objeto tipo Respuesta.
     * Define el metodo consultar() de la interfaz Broker
     * @return Respuesta objeto de tipo Respuesta que contiene el resultado de la operación correspondiente
     */
	@Override
	public synchronized Respuesta consultar() {
		
		Respuesta respuesta=null;
		String desc="";
		String estado="ERROR";
		int resultado=0;
		FactoryRespuesta factoryRta = new FactoryRespuesta(); 
		
		try {
			
			if (this.consulta!="") {
				conexion.getConexion().setAutoCommit(false);
				PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
				statement.setString(1,mensaje.getUsuario());
				statement.setString(2,mensaje.getPassword());
				
				
				resultado=statement.executeUpdate();
				conexion.getConexion().setAutoCommit(true);
				if (resultado!=0) {
					estado="OK";
					desc="Usuario agregado correctamente";
				}
				
			}else{
				desc="Password incorrecto";
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
			pass=rs.getString("password");
			
			
			conexion.getConexion().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
