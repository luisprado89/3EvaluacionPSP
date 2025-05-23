package BoletinesSolucionesProfesora.Boletin2.Ejercicio3; // Paquete del ejercicio

import java.io.Serializable; // Necesario para que el objeto pueda enviarse por sockets

@SuppressWarnings("serial") // Suprime advertencia por no declarar serialVersionUID
public class Numeros implements Serializable { // Clase que representa un número y sus potencias

	int numero;       // Valor introducido por el usuario
	long cuadrado;    // El cuadrado del número
	long cubo;        // El cubo del número

	// Constructor vacío (necesario para la deserialización)
	public Numeros() {
		super(); // Llama al constructor de Object
	}

	// Constructor que inicializa solo el número (cuadrado y cubo se calcularán luego)
	public Numeros(int numero) {
		this.numero = numero;
	}

	// Constructor que inicializa todos los atributos
	public Numeros(int numero, long cuadrado, long cubo) {
		super();
		this.numero = numero;
		this.cuadrado = cuadrado;
		this.cubo = cubo;
	}

	// Getters y setters

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public long getCuadrado() {
		return cuadrado;
	}

	public void setCuadrado(long cuadrado) {
		this.cuadrado = cuadrado;
	}

	public long getCubo() {
		return cubo;
	}

	public void setCubo(long cubo) {
		this.cubo = cubo;
	}
}
