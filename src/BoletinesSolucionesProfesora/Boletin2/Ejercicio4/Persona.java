package BoletinesSolucionesProfesora.Boletin2.Ejercicio4; // Paquete del ejercicio

import java.io.Serializable; // Interfaz necesaria para permitir la serialización

@SuppressWarnings("serial") // Suprime la advertencia por no declarar serialVersionUID
public class Persona implements Serializable { // Clase que representa a una persona, serializable para poder enviarla por red

	String nombre; // Atributo que almacena el nombre de la persona
	int edad;      // Atributo que almacena la edad de la persona

	// Constructor con parámetros para inicializar los atributos
	public Persona(String nombre, int edad) {
		super(); // Llama al constructor de la superclase (Object)
		this.nombre = nombre;
		this.edad = edad;
	}

	// Constructor vacío (necesario para la deserialización)
	public Persona() {
		super(); // Llamada al constructor por defecto
	}

	// Getter para obtener el nombre
	public String getNombre() {
		return nombre;
	}

	// Setter para modificar el nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Getter para obtener la edad
	public int getEdad() {
		return edad;
	}

	// Setter para modificar la edad
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
