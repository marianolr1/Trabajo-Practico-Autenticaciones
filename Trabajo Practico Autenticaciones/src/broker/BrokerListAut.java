package broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import conexionBD.Conexion;
import mensajes.*;
import respuesta.*;

public class BrokerListAut implements Broker {
	
	private String consulta="";
	private MListarAutenticaciones mensaje=null;
	private Conexion conexion;

	public BrokerListAut(MListarAutenticaciones mensaje) {
		this.mensaje=mensaje;
		conexion=Conexion.getInstance();
		if (claveCorrecta(mensaje.getPasswordAdmin()) && existeUsuario(mensaje.getUsuario())) {
			this.consulta="select host,ifnull(timestamp,now()) as timestamp from autenticaciones where username=?";
		}
	}

	@Override
	public Respuesta consultar() {
		RListarAutenticaciones respuesta=null;
		LinkedList<Autenticacion> lista=new LinkedList<Autenticacion> ();
		String res="Clave incorrecta";
		Autenticacion autenticacion=null;
		
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
		respuesta=new RListarAutenticaciones(lista,res);
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
