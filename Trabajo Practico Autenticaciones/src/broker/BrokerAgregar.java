package broker;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;

import java.sql.*;
import java.time.LocalDate;

public class BrokerAgregar implements Broker {
	private String consulta="";
	private Agregar mensaje=null;
	private Conexion conexion;//=Conexion.getInstance();

	public BrokerAgregar(Agregar mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		boolean tru=claveCorrecta(mensaje.getPasswordAdmin());
		if (tru) {
			this.consulta="insert into usuarios (`username`, `password`,`timestamp`, `isadmin`) values(?,?,now(),0)";
		}
		
	}

	@Override
	public Respuesta consultar() {
		
		
		Estado respuesta=null;
		String desc="";
		String estado="ERROR";
		int resultado=0;
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
		respuesta=new Estado(estado,desc);
	
		return respuesta;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
