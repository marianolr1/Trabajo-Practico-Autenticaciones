package conexionBD;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuracion {

	private Properties propiedades;
	private static Configuracion instancia=null;
	private Configuracion() {
		try {
			propiedades=new Properties(); 
			String rutaArchivo = System.getProperty("user.dir")+System.getProperty("file.separator")
	         + "configConexion.properties";
			 FileInputStream is = new FileInputStream(rutaArchivo);
			 propiedades.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Configuracion getInstance(){
		if (instancia==null) {
			instancia=new Configuracion();
		}
		return instancia;
	}

	public Properties getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Properties propiedades) {
		this.propiedades = propiedades;
	}

}
