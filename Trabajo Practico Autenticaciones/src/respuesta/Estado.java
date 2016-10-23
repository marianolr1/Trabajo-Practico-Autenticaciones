package respuesta;
/**
 * Clase que define la respuesta de tipo Estado.
 * Utilizada para los mensajes de tipo Agregar, Modificar, Remover, Autenticar.
 * Ademas se usa para generar respuesta de error para los mensajes MListarAutenticaciones y MListarUsuarios.
 */
public class Estado extends Respuesta {

	private String estado;
	private String descripcion;
    /**
     * Constructor de la clase
     * @param estado Estado de la respuesta
     * @param desc Descripcion de la respuesta
     */
	public Estado(String estado, String desc) {
		this.estado=estado;
		this.descripcion=desc;
	}

	/**
	 * Setters/Getters
	 */
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

}
