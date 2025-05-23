package BoletinesSolucionesProfesora.Boletin1.Ejercicio5; // Paquete donde se encuentra esta clase

import java.io.*; // Importa clases para manejo de flujos de entrada/salida
import java.net.*; // Importa clases para trabajar con sockets

public class ejercicio5_Servidor { // Clase principal del servidor

	public static void main(String[] arg) throws IOException { // Método principal, puede lanzar IOException
		int Puerto = 6000; // Puerto por el que el servidor estará escuchando

		// INICIO EL SERVIDOR
		ServerSocket Servidor = new ServerSocket(Puerto); // Crea el servidor y lo pone a escuchar en el puerto indicado
		System.out.println("Escuchando en el puerto " + Puerto); // Muestra mensaje indicando que el servidor está listo

		// Acepto peticiones de 3 clientes
		for (int i = 1; i <= 3; i++) { // Bucle para aceptar hasta 3 conexiones
			Socket Cliente = Servidor.accept(); // Espera a que se conecte un cliente. Es una llamada bloqueante.
			System.out.println("Sirviendo al cliente " + i); // Informa qué número de cliente está siendo atendido

			// CREO FLUJO DE SALIDA AL CLIENTE
			OutputStream aux = Cliente.getOutputStream(); // Obtiene el flujo de salida del socket
			DataOutputStream flujo = new DataOutputStream(aux); // Permite enviar datos primitivos, como cadenas UTF

			// LE ENVÍO UN SALUDO
			flujo.writeUTF("Saludo al cliente " + i); // Envia al cliente un mensaje personalizado

			Cliente.close(); // Cierra el socket con el cliente tras enviar el mensaje
		}

		Servidor.close(); // Cierra el servidor una vez ha atendido a los 3 clientes
		System.out.println("No hay más clientes..."); // Informa que ha terminado
	} // Fin del main
} // Fin de la clase
