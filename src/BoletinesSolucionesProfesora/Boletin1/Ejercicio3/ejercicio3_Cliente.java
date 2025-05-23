package BoletinesSolucionesProfesora.Boletin1.Ejercicio3;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class ejercicio3_Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Nombre del host al que se conectará (en este caso, el propio equipo)
		String host = "localhost";

		// Puerto en el que el servidor está escuchando (debe coincidir con el puerto del servidor)
		int puerto = 6000; // puerto remoto

		// ABRIR SOCKET
		// Crea un socket y establece conexión con el servidor en la dirección y puerto especificados
		Socket cliente = new Socket(host, puerto); // conecta al servidor en localhost:6000

		// Obtiene la dirección IP remota (del servidor)
		InetAddress i = cliente.getInetAddress();

		// Muestra el puerto local del cliente (el puerto desde el que este programa se conecta)
		System.out.println("Puerto local: " + cliente.getLocalPort());

		// Muestra el puerto remoto al que está conectado (el puerto del servidor)
		System.out.println("Puerto Remoto: " + cliente.getPort());

		// Muestra la dirección IP o nombre del host remoto (servidor)
		System.out.println("Nombre Host/IP: " + cliente.getInetAddress());

		// Muestra el nombre del host remoto (obtenido desde el objeto InetAddress)
		System.out.println("Host Remoto: " + i.getHostName().toString());

		// Muestra la dirección IP del host remoto
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

		// Cierra la conexión del socket
		cliente.close();
	}
}
