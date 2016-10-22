package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static Conexion instancia=null;
	private Connection conexion;
	private final String url;// = "jdbc:mysql://Profe02";//"jdbc:mysql://localhost";
	private final String port;// ="3306";
	private final String dbName;// = "test";
	private final String driver;// = "com.mysql.jdbc.Driver";//agregar en config
	private final String userName;// = "root";
	private final String password;// = "fasta";
	private Configuracion configuracion;
	/**
     * Constructor privado de la clase.
     * configuracion,  contiene los parametros de conexion leidos de un archivo de properties
     */
	private Conexion(){
		configuracion= Configuracion.getInstance();
		
		//(String url,String port,String dbName,String userName,String password) {
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
		//(String url,String port,String dbName,String name,String password){
		if(instancia==null){
			instancia= new Conexion();//(url,port,dbName,name,password);
		}
		return instancia;
		
	}
	
	public void iniciarConexion(){
		try {			
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection(url+":"+port+"/"+dbName, userName, password);
			if(!conexion.isClosed()){
				System.out.println("Conexión establecida");
			}
		} catch (Exception e) {
			System.out.println("Conexión no establecida");
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

}
