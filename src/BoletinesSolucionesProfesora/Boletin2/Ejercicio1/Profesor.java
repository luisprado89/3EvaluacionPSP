package BoletinesSolucionesProfesora.Boletin2.Ejercicio1; // Paquete donde se encuentra la clase

import java.io.Serializable; // Permite que los objetos puedan enviarse por red mediante sockets

@SuppressWarnings("serial") // Suprime advertencia por no declarar serialVersionUID
public class Profesor implements Serializable { // Clase Profesor que implementa Serializable

	int idprofesor; // Identificador del profesor
	String nombre; // Nombre del profesor
	Asignatura[] asignaturas; // Array de asignaturas que imparte (máximo 3 según el enunciado)
	Especialidad espe; // Especialidad del profesor (objeto de tipo Especialidad)

	// Constructor con todos los atributos
	public Profesor(int idprofesor, String nombre, Asignatura[] asignaturas, Especialidad espe) {
		super(); // Llama al constructor de la superclase (Object)
		this.idprofesor = idprofesor; // Asigna el ID recibido
		this.nombre = nombre; // Asigna el nombre recibido
		this.asignaturas = asignaturas; // Asigna el array de asignaturas
		this.espe = espe; // Asigna la especialidad
	}

	// Getter para obtener el ID del profesor
	public int getIdprofesor() {
		return idprofesor;
	}

	// Setter para modificar el ID del profesor
	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}

	// Getter para obtener el nombre del profesor
	public String getNombre() {
		return nombre;
	}

	// Setter para modificar el nombre del profesor
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Getter para obtener las asignaturas del profesor
	public Asignatura[] getAsignaturas() {
		return asignaturas;
	}

	// Setter para modificar las asignaturas del profesor
	public void setAsignaturas(Asignatura[] asignaturas) {
		this.asignaturas = asignaturas;
	}

	// Getter para obtener la especialidad del profesor
	public Especialidad getEspe() {
		return espe;
	}

	// Setter para modificar la especialidad del profesor
	public void setEspe(Especialidad espe) {
		this.espe = espe;
	}
}
