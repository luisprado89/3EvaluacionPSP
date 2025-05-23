package BoletinesSolucionesProfesora.Boletin2.Ejercicio5; // Paquete del ejercicio

import java.io.Serializable; // Necesario para que el objeto se pueda enviar por red

public class Alumno implements Serializable { // Clase que representa a un alumno y se puede serializar

	String idalumno;  // Identificador único del alumno
	String nombre;    // Nombre del alumno
	Curso curso;      // Objeto Curso asociado al alumno
	int nota;         // Nota del alumno

	// Constructor vacío (necesario para la deserialización)
	public Alumno() {
	}

	// Constructor con todos los atributos
	public Alumno(String idalumno, String nombre, Curso curso, int nota) {
		super(); // Llama al constructor de la clase padre (Object)
		this.idalumno = idalumno;
		this.nombre = nombre;
		this.curso = curso;
		this.nota = nota;
	}

	// Getters y setters

	public String getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(String idalumno) {
		this.idalumno = idalumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}
