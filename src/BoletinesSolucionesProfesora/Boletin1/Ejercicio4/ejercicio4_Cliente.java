package BoletinesSolucionesProfesora.Boletin1.Ejercicio4; // Define el paquete del ejercicio

import java.io.*; // Importa clases para manejar flujos de entrada/salida
import java.net.Socket; // Importa la clase Socket para crear conexión TCP
import java.util.InputMismatchException; // Para controlar errores al leer números
import java.util.Scanner; // Para leer datos del teclado

/**
 * Boletín 1 SOCKETS
 * Ejercicio 4
 * Crea un programa cliente que introduzca por teclado un número entero
 * y se lo envíe al servidor. El servidor le devolverá el cuadrado del número.
 */

public class ejercicio4_Cliente { // Clase principal del cliente

	public static void main(String[] args) throws IOException { // Método principal que puede lanzar IOException
		String Host = "localhost"; // Dirección del servidor (local en este caso)
		int Puerto = 6000; // Puerto remoto al que conectarse (debe coincidir con el del servidor)

		Socket Cliente = new Socket(Host, Puerto); // Se crea y abre una conexión TCP al servidor

		// CREO FLUJO DE SALIDA AL SERVIDOR
		OutputStream salida = null; // Flujo de salida básico
		salida = Cliente.getOutputStream(); // Obtiene el flujo de salida del socket
		DataOutputStream flujoSalida = new DataOutputStream(salida); // Permite enviar datos primitivos (como int)

		// CREO FLUJO DE ENTRADA AL SERVIDOR
		InputStream fentrada = null; // Flujo de entrada básico
		fentrada = Cliente.getInputStream(); // Obtiene el flujo de entrada del socket
		DataInputStream flujoEntrada = new DataInputStream(fentrada); // Permite leer datos primitivos (como int)

		// ENTRADA ESTÁNDAR
		Scanner sc = new Scanner(System.in); // Para leer datos desde el teclado

		System.out.print("Introduce un número: "); // Pide al usuario un número entero

		Integer n;
		try {
			n = sc.nextInt(); // Lee el número introducido por el usuario
		} catch (InputMismatchException e) { // Si el usuario introduce texto o algo no válido
			System.out.print("Número introducido incorrecto."); // Muestra error
			Cliente.close(); // Cierra la conexión
			return; // Termina el programa
		}

		// Alternativa comentada para leer como cadena y parsear manualmente:
		/*
		String cadena = sc.nextLine();
		Integer n;
		try {
			n = Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			System.out.print("Número introducido incorrecto.");
			Cliente.close();
			return;
		}
		*/

		flujoSalida.writeInt(n); // Envía el número entero al servidor

		int cuadrado = flujoEntrada.readInt(); // Espera y recibe el resultado (el cuadrado del número)

		System.out.println("EL CUADRADO ES => " + cuadrado); // Muestra el resultado recibido

		flujoSalida.close(); // Cierra el flujo de salida
		flujoEntrada.close(); // Cierra el flujo de entrada

		System.out.println("Fin de proceso... "); // Mensaje de cierre

		Cliente.close(); // Cierra el socket
	}
}
