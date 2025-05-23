package BoletinesSolucionesProfesora.Boletin2.Ejercicio5; // Paquete del ejercicio

import java.io.Serializable; // Necesario para permitir enviar el objeto por red

public class Curso implements Serializable { // Clase serializable para incluir dentro de Alumno

	String id;           // Identificador del curso (ej. "DAW1", "INF3")
	String descripcion;  // Descripción del curso (ej. "Desarrollo de Aplicaciones Web")

	// Constructor vacío (necesario para deserialización automática)
	public Curso() {
		// Constructor por defecto
	}

	// Constructor completo para inicializar el curso
	public Curso(String id, String descripcion) {
		super(); // Llama al constructor de la clase Object
		this.id = id;
		this.descripcion = descripcion;
	}

	// Getter del ID del curso
	public String getId() {
		return id;
	}

	// Setter del ID del curso
	public void setId(String id) {
		this.id = id;
	}

	// Getter de la descripción del curso
	public String getDescripcion() {
		return descripcion;
	}

	// Setter de la descripción del curso
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
