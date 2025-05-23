package BoletinesSolucionesProfesora.Boletin2.Ejercicio5;

import java.io.Serializable;

public class Curso implements Serializable {
	String id;
	String descripcion;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(String id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
