package server;


public class Tarea {

	// Variables proporcionados para la actividad.
	private String descripcion;
	private String estado;
	
	// Creación de Tarea
	
	public Tarea(){}
	
	public Tarea(String descripcion, String estado) {
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	// getter y setter de la descripción
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// getter y setter del estado.
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
