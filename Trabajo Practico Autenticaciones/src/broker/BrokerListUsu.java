package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;

public class BrokerListUsu implements Broker {
	
	private String consulta="";
	private MListarUsuarios mensaje=null;
	private Conexion conexion;

	public BrokerListUsu(MListarUsuarios mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(this.mensaje.getPasswordAdmin())) {
			this.consulta="select username,ifnull(timestamp,now()) as timestamp  from usuarios";
		}
	}

	@Override
	public Respuesta consultar() {
		RListarUsuarios respuesta=null;
		LinkedList<Usuario> lista=new LinkedList<Usuario> ();
		String res="Clave incorrecta";
		Usuario usuario=null;
		
		ResultSet rs=null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass.equals(passAdmin);
	}

}
