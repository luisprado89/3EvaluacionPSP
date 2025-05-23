package BoletinesSolucionesProfesora.Boletin1.Ejercicio3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Boletín 1 SOCKETS
* Ejercicio 3
* Realiza un programa servidor TCP que acepte 2 clientes.
* Para cada cliente, mostrar sus puertos local y remoto.
* Se deberá implementar también el programa cliente que se conecte a dicho servidor.
* Mostrar los puertos
* locales y remotos a los que está conectado su socket, y la dirección IP de la máquina remota a la que se
* conecta.
*
* */



public class ejercicio3_Servidor {

	public static void main(String[] args) throws IOException {

		int puerto = 6000; // Define el puerto en el que escuchará el servidor (puerto del servidor)
		ServerSocket servidor = new ServerSocket(puerto); // Crea un socket servidor que escucha en el puerto 6000
		System.out.println("Escuchando en " + servidor.getLocalPort()); // Muestra el puerto en el que está escuchando el servidor

		System.out.println("Esperando primer cliente");
		Socket cliente1 = servidor.accept(); // Espera a que un cliente se conecte. Cuando lo hace, devuelve el socket del cliente
		System.out.println("Primer cliente atendido"); // Mensaje indicando que el cliente se ha conectado

		// Muestra el puerto local (el del servidor) y el remoto (el del cliente) del socket cliente1
		System.out.println("Puerto del cliente 1 getLocalPort(): " + cliente1.getLocalPort()); // Puerto local del socket (el del servidor)
		System.out.println("Puerto del cliente 1 getPort(): " + cliente1.getPort()); // Puerto remoto (el del cliente que se ha conectado)

		System.out.println("Esperando segundo cliente");
		Socket cliente2 = servidor.accept(); // Espera a que se conecte un segundo cliente
		System.out.println("Segundo cliente atendido"); // Indica que el segundo cliente se ha conectado

		// Muestra los puertos del segundo cliente (igual que arriba)
		System.out.println("Puerto del cliente 2 getLocalPort(): " + cliente2.getLocalPort()); // Puerto local del socket
		System.out.println("Puerto del cliente 2 getPort(): " + cliente2.getPort()); // Puerto remoto (el del segundo cliente)

		// Aquí se podrían realizar acciones con el cliente2, si fuera necesario

		servidor.close(); // Cierra el socket del servidor (ya no acepta más conexiones)
	}
}