package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.Estado;
import respuesta.Respuesta;

public class BrokerRemover implements Broker {
	
	private String consulta="";
	private Remover mensaje=null;
	private Conexion conexion;

	public BrokerRemover(Remover mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(mensaje.getPasswordAdmin())) {
			this.consulta="delete from usuarios "
					+ "where username=?";
		}
	}

	@Override
	public synchronized Respuesta consultar() {
		Estado respuesta=null;
		String desc="";
		String estado="ERROR";
		int rs=0;
		try {
			if (this.consulta!="") {
				conexion.getConexion().setAutoCommit(false);
				PreparedStatement statement=conexion.getConexion().prepareStatement(consulta);
				statement.setString(1,mensaje.getUsuario());
				rs=statement.executeUpdate();
				conexion.getConexion().setAutoCommit(true);
				if (rs!=0) {
					estado="OK";
					desc="Usuario eliminado";
				}	
			}else {
				estado="ERROR";
				desc="Clave incorrecta";
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
			pass=rs.getString(1);
			conexion.getConexion().setAutoCommit(true);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
