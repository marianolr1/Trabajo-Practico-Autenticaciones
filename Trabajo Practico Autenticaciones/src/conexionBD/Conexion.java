package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static Conexion instancia=null;
	private Connection conexion;
	private final String url;
	private final String port;
	private final String dbName;
	private final String driver;
	private final String userName;
	private final String password;
	private Configuracion configuracion;
	/**
     * Constructor privado de la clase.
     * configuracion,  contiene los parametros de conexion leidos de un archivo de properties
     */
	private Conexion(){
		configuracion= Configuracion.getInstance();
		this.driver=configuracion.getPropiedades().getProperty("driver");
		this.url=configuracion.getPropiedades().getProperty("url");
		this.port=configuracion.getPropiedades().getProperty("port");
		this.dbName=configuracion.getPropiedades().getProperty("dbName");
		this.userName=configuracion.getPropiedades().getProperty("userName");
		this.password=configuracion.getPropiedades().getProperty("password");
		
		iniciarConexion();
	}
	/**
     * Metodo estatico para obtener la instancia de conexion, o crear una en caso de que no exista
     * @return Variable "instancia" con la instancia de conexion
     */
	public static Conexion getInstance(){
		if(instancia==null){
			instancia= new Conexion();
		}
		return instancia;
		
	}
	
	public void iniciarConexion(){
		try {			
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection(url+":"+port+"/"+dbName, userName, password);
			
		} catch (Exception e) {
			System.out.println("Conexión no establecida con el servidor de la Base de Datos");
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

}
