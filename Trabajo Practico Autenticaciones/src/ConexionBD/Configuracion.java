package ConexionBD;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuracion {

	private static Properties propiedades;
	private Configuracion() {
		try {
			 String rutaArchivo = System.getProperty("user.dir")+System.getProperty("file.separator")
	         + "configConexion.properties";
			 FileInputStream is = new FileInputStream(rutaArchivo);
			 propiedades.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static final Properties getInstance(){
		if (propiedades==null) {
			propiedades=new Configuracion().propiedades;
		}
		return propiedades;
	}

}
