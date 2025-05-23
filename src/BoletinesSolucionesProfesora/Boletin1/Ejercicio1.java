package BoletinesSolucionesProfesora.Boletin1;
/*
 * Boletín 1 SOCKETS
 * Ejercicio 1
 * Realiza un programa Java que admita desde consola nombres de máquinas o direcciones IP y
 *vaya mostrando por pantalla información sobre ellas, haciendo uso de la clase InetAddress.
 */
import java.net.*;
import java.util.Scanner;

public class Ejercicio1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in); // Se crea un objeto Scanner para leer datos del usuario por consola
		String maquina = ""; // Variable que almacenará la dirección IP o nombre introducido por el usuario
		InetAddress dir = null; // Variable para almacenar la dirección IP convertida en un objeto InetAddress

		while (!maquina.equals("*")) { // Bucle que se ejecuta mientras no se introduzca el asterisco como salida
			System.out.println("Introduzca una IP:"); // Se solicita al usuario que introduzca una IP o nombre de máquina
			maquina = sc.nextLine(); // Se lee la línea introducida por el usuario

			if (!maquina.equals("*")) { // Si el usuario no ha introducido "*", se continúa con la lógica
				try {
					dir = InetAddress.getByName(maquina); // Obtiene un objeto InetAddress a partir del nombre/IP
					pruebaMetodos(dir); // Llama al método que muestra información detallada sobre el objeto InetAddress
				} catch (UnknownHostException e) { // Captura la excepción si no se puede resolver el nombre/IP
					System.out.println("HOST DESCONOCIDO"); // Muestra mensaje de error
					System.out.println(e.getMessage()); // Muestra el mensaje detallado del error
					System.exit(0); // Termina la ejecución del programa
				}

				// Intenta obtener todas las direcciones IP asociadas al nombre del host
				try {
					InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName()); // Obtiene todas las direcciones asociadas al hostname
					for (int i = 0; i < direcciones.length; i++) // Recorre el array de direcciones
						System.out.println(direcciones[i].toString()); // Muestra cada dirección por pantalla
				} catch (UnknownHostException e) { // Captura cualquier excepción que pueda producirse
					e.printStackTrace(); // Imprime el rastro del error
				}
			}
		}
	} // Fin del método main

	// Método que muestra información detallada del objeto InetAddress recibido
	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName():  " + dir); // Muestra la dirección que se obtuvo con getByName

		InetAddress dir2; // Variable para la dirección IP local
		try {
			dir2 = InetAddress.getLocalHost(); // Obtiene la dirección IP del equipo local
			System.out.println("\tMetodo getLocalHost(): " + dir2); // Muestra la IP del equipo local
		} catch (UnknownHostException e) { // Captura la excepción si falla al obtener la IP local
			e.printStackTrace(); // Imprime el rastro del error
		}

		// Se muestran diferentes métodos de la clase InetAddress aplicados a 'dir'
		System.out.println("\tMetodo getHostName(): " + dir.getHostName()); // Devuelve el nombre del host
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress()); // Devuelve la IP del host
		System.out.println("\tMetodo toString(): " + dir.toString()); // Representación completa del objeto
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName()); // Devuelve el nombre canónico del host
	} // Fin del método pruebaMetodos
} // Fin de la clase Ejercicio1