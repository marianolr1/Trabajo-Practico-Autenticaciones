package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

	private static Conexion instancia=null;
	private Connection conexion;
	private final String url;// = "jdbc:mysql://Profe02";//"jdbc:mysql://localhost";
	private final String port;// ="3306";
	private final String dbName;// = "test";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName;// = "root";
	private final String password;// = "fasta";
	private Configuracion configuracion;
	private Conexion(){
		configuracion= Configuracion.getInstance();
		
		//(String url,String port,String dbName,String userName,String password) {
		this.url=configuracion.getPropiedades().getProperty("url");
		this.port=configuracion.getPropiedades().getProperty("port");
		this.dbName=configuracion.getPropiedades().getProperty("dbName");
		this.userName=configuracion.getPropiedades().getProperty("userName");
		this.password=configuracion.getPropiedades().getProperty("password");
		
		iniciarConexion();
	}
	
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

}
