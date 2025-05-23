package BoletinesSolucionesProfesora.Boletin2.Ejercicio1; // Paquete en el que se encuentra la clase

import java.io.Serializable; // Importa la interfaz Serializable para permitir que el objeto se envíe por red

@SuppressWarnings("serial") // Suprime advertencia sobre la falta de un campo serialVersionUID
public class Asignatura implements Serializable { // Clase Asignatura que implementa Serializable para permitir su envío por sockets

	int id; // Identificador único de la asignatura
	String nombreasig; // Nombre de la asignatura

	// Constructor de la clase Asignatura
	public Asignatura(int id, String nombreasig) {
		super(); // Llama al constructor de la superclase (Object). No es necesario explícitamente, pero es común
		this.id = id; // Asigna el ID recibido al atributo de clase
		this.nombreasig = nombreasig; // Asigna el nombre recibido al atributo de clase
	}

	// Método getter para obtener el ID de la asignatura
	public int getId() {
		return id;
	}

	// Método setter para modificar el ID de la asignatura
	public void setId(int id) {
		this.id = id;
	}

	// Método getter para obtener el nombre de la asignatura
	public String getNombreasig() {
		return nombreasig;
	}

	// Método setter para modificar el nombre de la asignatura
	public void setNombreasig(String nombreasig) {
		this.nombreasig = nombreasig;
	}
}
