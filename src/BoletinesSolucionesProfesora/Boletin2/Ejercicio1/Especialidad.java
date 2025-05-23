package BoletinesSolucionesProfesora.Boletin2.Ejercicio1; // Paquete donde se encuentra la clase

import java.io.Serializable; // Necesario para que los objetos se puedan enviar por sockets (serialización)

@SuppressWarnings("serial") // Suprime la advertencia por no declarar serialVersionUID
public class Especialidad implements Serializable { // Clase que representa una especialidad y es serializable

	int id; // Identificador único de la especialidad
	String nombreespe; // Nombre de la especialidad

	// Constructor de la clase con parámetros para inicializar los atributos
	public Especialidad(int id, String nombreespe) {
		super(); // Llama al constructor de la superclase (Object), aunque no es obligatorio
		this.id = id; // Asigna el parámetro id al campo de instancia
		this.nombreespe = nombreespe; // Asigna el parámetro nombreespe al campo de instancia
	}

	// Método getter: devuelve el id de la especialidad
	public int getId() {
		return id;
	}

	// Método setter: establece un nuevo id
	public void setId(int id) {
		this.id = id;
	}

	// Método getter: devuelve el nombre de la especialidad
	public String getNombreespe() {
		return nombreespe;
	}

	// Método setter: permite cambiar el nombre de la especialidad
	public void setNombreespe(String nombreespe) {
		this.nombreespe = nombreespe;
	}
}
