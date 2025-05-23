package BoletinesSolucionesProfesora.Boletin1.Ejercicio6; // Paquete donde se encuentra la clase

import java.io.*; // Importa clases de entrada/salida
import java.net.*; // Importa clases de red (ServerSocket, Socket, etc.)

public class ejercicio6_Servidor { // Clase principal del servidor

	public static void main(String[] arg) throws IOException { // Método principal
		int numeroPuerto = 6000; // Puerto en el que el servidor estará escuchando
		ServerSocket servidor = new ServerSocket(numeroPuerto); // Se crea el socket servidor

		String cad = ""; // Variable que almacenará cada cadena recibida

		System.out.println("Esperando conexion..."); // Mensaje de espera
		Socket cliente = servidor.accept(); // Espera la conexión de un cliente
		System.out.println("Cliente conectado..."); // Confirma que el cliente se conectó

		// CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = null; // Flujo de salida base
		salida = cliente.getOutputStream(); // Obtiene el flujo de salida desde el socket cliente
		DataOutputStream fsalida = new DataOutputStream(salida); // Permite enviar datos primitivos como enteros

		// CREO FLUJO DE ENTRADA DEL CLIENTE
		InputStream entrada = null; // Flujo de entrada base
		entrada = cliente.getInputStream(); // Obtiene el flujo de entrada del cliente
		DataInputStream flujoEntrada = new DataInputStream(entrada); // Permite leer cadenas UTF y otros datos primitivos

		// PRIMERA LECTURA DE CADENA
		cad = flujoEntrada.readUTF(); // Lee la primera cadena enviada por el cliente

		while (!cad.equals("*")) { // Mientras la cadena no sea "*", continúa
			Integer n = cad.length(); // Calcula la longitud de la cadena
			System.out.println("Recibiendo: " + cad + " enviando: " + n); // Muestra la cadena recibida y su longitud
			fsalida.writeInt(n); // Envía la longitud de la cadena de vuelta al cliente

			cad = flujoEntrada.readUTF(); // Lee la siguiente cadena
		}

		// CERRAR STREAMS Y SOCKETS
		System.out.println("Cerrando conexion..."); // Mensaje de cierre

		flujoEntrada.close(); // Cierra flujo de entrada
		fsalida.close(); // Cierra flujo de salida
		cliente.close(); // Cierra conexión con el cliente
		servidor.close(); // Cierra el servidor
	}
}
