package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;
/**
 * Clase que define al objeto Broker del mensaje MListarUsuarios (Listado de Usuarios).
 * Constituye la creacion y ejecucion de la consulta a la base de datos.
 * Implementa a la interfaz Broker
 * @author Mariano Cortinez, Pablo Cassettai
 */
public class BrokerListUsu implements Broker {
	
	private String consulta="";
	private MListarUsuarios mensaje=null;
	private Conexion conexion;

    /**
     * Constructor de la clase
     * @param mensaje Objeto Mensaje de tipo MListarUsuarios (Listado de Usuarios)
     */
	public BrokerListUsu(MListarUsuarios mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(this.mensaje.getPasswordAdmin())) {
			this.consulta="select username,ifnull(timestamp,now()) as timestamp  from usuarios";
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
		LinkedList<Usuario> lista=new LinkedList<Usuario> ();
		String res="Clave incorrecta";
		Usuario usuario=null;
		ResultSet rs=null;
		FactoryRespuesta factoryRta = new FactoryRespuesta();
		
		try {
			if (this.consulta!="") {
				conexion.getConexion().setAutoCommit(false);
				PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
				rs=statement.executeQuery();
				conexion.getConexion().setAutoCommit(true);
				if (!rs.next()) {
					res="No hay usuarios";
				}else {
					rs.previous();
					while (rs.next()) {
						usuario=new Usuario(rs.getString(1),rs.getDate(2).toLocalDate());
						lista.add(usuario);
					}
					res="OK";
				}	
			}else {
				
			}
			} catch (Exception e) {
				e.printStackTrace();
				res="Error de conexion";
		}
		respuesta=new RListarUsuarios(lista,res);
		
		LinkedList<Autenticacion> lautenticaciones = null; 	// parametro no utilizado
		String estado = null;								// parametro no utilizado 
		respuesta= factoryRta.crearRespuesta("LIST-USERS", estado, res, lautenticaciones, lista);
		
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
