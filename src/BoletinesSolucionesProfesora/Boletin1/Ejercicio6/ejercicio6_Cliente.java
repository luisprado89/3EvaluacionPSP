package BoletinesSolucionesProfesora.Boletin1.Ejercicio6; // Paquete donde se encuentra la clase

/*
 * Boletín 1 SOCKETS
 * Ejercicio 6
 * Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta
 * introducir un asterisco. Las cadenas se enviarán a un programa servidor. El programa servidor
 * aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente el
 * número de caracteres de la misma. El programa servidor finalizará cuando reciba un asterisco
 * como cadena.
 */

import java.io.*; // Para entrada/salida de datos
import java.net.*; // Para clases de red (Socket, etc.)
import java.util.Scanner; // Para leer desde teclado

public class ejercicio6_Cliente { // Clase principal del cliente

	public static void main(String[] args) throws IOException { // Método principal
		String host = "localhost"; // Dirección del servidor (en este caso, el mismo equipo)
		int puerto = 6000; // Puerto remoto en el que escucha el servidor

		Socket cliente = new Socket(host, puerto); // Crea un socket y se conecta al servidor

		// CREO FLUJO DE SALIDA AL SERVIDOR
		OutputStream salida = null; // Flujo de salida básico
		salida = cliente.getOutputStream(); // Obtiene el flujo de salida del socket
		DataOutputStream fsalida = new DataOutputStream(salida); // Permite enviar datos como cadenas UTF

		// CREO FLUJO DE ENTRADA DE SERVIDOR
		InputStream entrada = null; // Flujo de entrada básico
		entrada = cliente.getInputStream(); // Obtiene el flujo de entrada del socket
		DataInputStream flujoEntrada = new DataInputStream(entrada); // Permite recibir datos primitivos como enteros

		String cadena; // Variable para almacenar las cadenas que se introducirán
		Scanner sc = new Scanner(System.in); // Objeto para leer desde teclado

		System.out.print("Introduce cadena: "); // Solicita la primera cadena al usuario
		cadena = sc.nextLine(); // Lee la cadena introducida

		while(!cadena.equals("*")){ // Mientras no se introduzca "*", continúa
			fsalida.writeUTF(cadena); // Envía la cadena al servidor

			Integer longitud = flujoEntrada.readInt(); // Recibe la longitud de la cadena
			System.out.print("\tNumero de caracteres: " + longitud); // Muestra la longitud recibida

			System.out.println("Introduce cadena: "); // Pide la siguiente cadena
			cadena = sc.nextLine(); // La lee
		}

		fsalida.writeUTF("*"); // Envía "*" para indicar al servidor que finalice

		// Cierra todos los flujos y el socket
		fsalida.close();
		flujoEntrada.close();
		System.out.println("Fin de proceso... ");
		cliente.close();
	} // Fin del main
} // Fin de la clase
