package respuesta;

public class Estado extends Respuesta {

	private String estado;
	private String descripcion;
	
	public Estado(String estado, String desc) {
		this.estado=estado;
		this.descripcion=desc;
	}
//Region - Setters/Getters
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
//EndRegion
}
