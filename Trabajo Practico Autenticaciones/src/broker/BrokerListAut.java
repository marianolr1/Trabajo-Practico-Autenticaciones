package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;
/**
 * Clase que define al objeto Broker del mensaje MListarAutenticaciones (Listado de Autenticaciones).
 * Constituye la creacion y ejecucion de la consulta a la base de datos.
 * Implementa a la interfaz Broker
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class BrokerListAut implements Broker {
	
	private String consulta="";
	private MListarAutenticaciones mensaje=null;
	private Conexion conexion;
	
    /**
     * Constructor de la clase
     * @param mensaje Objeto Mensaje de tipo MListarAutenticaciones (Listado de Autenticaciones)
     */
	public BrokerListAut(MListarAutenticaciones mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(mensaje.getPasswordAdmin()) && existeUsuario(mensaje.getUsuario())) {
			this.consulta="select host,ifnull(timestamp,now()) as timestamp from autenticaciones where username=?";
		}
	}

    /**
     * Metodo que realiza la consulta a la base de datos.
     * Define el metodo consultar() de la interfaz Broker
     * @return Un objeto de tipo Respuesta, con la informacion necesaria para notificar al cliente el resultado de la operacion
     */
	@Override
	public Respuesta consultar() {
		Respuesta respuesta=null;
		LinkedList<Autenticacion> lista=new LinkedList<Autenticacion> ();
		String res="Clave incorrecta";
		Autenticacion autenticacion=null;
		FactoryRespuesta factoryRta = new FactoryRespuesta();
		
		ResultSet rs=null;
		try {
			if (this.consulta!="") {
				conexion.getConexion().setAutoCommit(false);
				PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
				statement.setString(1,mensaje.getUsuario());
				rs=statement.executeQuery();
				conexion.getConexion().setAutoCommit(true);
				if (!rs.next()) {
					res="El usuario no tiene autenticaciones";
				}else {
					rs.previous();
					while (rs.next()) {
						autenticacion=new Autenticacion(rs.getString(1),(rs.getDate(2).toLocalDate()));
						lista.add(autenticacion);
					}
					res="OK";
				}	
			}else {
				if  (!existeUsuario(mensaje.getUsuario())){
					res="USUARIO INCORRECTO";
				}
			}
			} catch (Exception e) {
				res="Error de conexion";
		}
		//respuesta=new RListarAutenticaciones(lista,res);
	
		LinkedList<Usuario> lusuarios  = null;	// parametro no utilizado
		String estado = null;					// parametro no utilizado
		respuesta= factoryRta.crearRespuesta("LIST-AUT", estado, res, lista, lusuarios);
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
	
	/**
	 * Metodo para verificar si existe el usuario
	 * @return retorna un boolean indicando si existe o no 
	 */
	public boolean existeUsuario(String usuario){
		String consulta="select username from usuarios where username=?";
		ResultSet rs;
		boolean res=true;
		try {
			conexion.getConexion().setAutoCommit(false);
			
			PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
			statement.setString(1,usuario);
			rs=statement.executeQuery();
			if (!rs.next()){
                res=false;
			}		
			
			conexion.getConexion().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
