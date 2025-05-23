package BoletinesSolucionesProfesora.Boletin2.Ejercicio6; // Paquete del ejercicio

import java.io.*;
import java.net.*;

public class Servidor {

	static ServerSocket servidor; // Socket del servidor que escucha conexiones entrantes
	static final int PUERTO = 12345; // Puerto en el que el servidor escucha

	public static void main(String args[]) throws IOException {

		// 1. INICIAR SERVIDOR
		servidor = new ServerSocket(PUERTO); // Crea el socket en el puerto 12345
		System.out.println("Servidor iniciado...");

		// 2. BUCLE INFINITO PARA ACEPTAR CLIENTES
		while (true) {
			Socket s;
			try {
				s = servidor.accept(); // Espera a que un cliente se conecte
			} catch (SocketException ns) {
				// Si ocurre una excepción (por ejemplo, si el socket se cierra), se rompe el bucle
				break;
			}

			// 3. LANZAR UN NUEVO HILO PARA ATENDER AL CLIENTE
			HiloServidor hilo = new HiloServidor(s); // Crea una instancia del hilo con el socket del cliente
			hilo.start(); // Inicia el hilo: ejecuta su método run()
		}

		// 4. SI EL BUCLE TERMINA (socket cerrado)
		System.out.println("Servidor finalizado...");
	}
}
