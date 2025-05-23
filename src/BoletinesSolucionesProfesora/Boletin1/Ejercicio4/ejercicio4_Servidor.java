package BoletinesSolucionesProfesora.Boletin1.Ejercicio4; // Paquete donde se encuentra esta clase

import java.io.*; // Importa clases para manejar flujos de entrada/salida
import java.net.ServerSocket; // Clase para crear el socket del servidor
import java.net.Socket; // Clase para manejar la conexión con el cliente

public class ejercicio4_Servidor { // Clase principal del servidor

	public static void main(String[] args) throws IOException { // Método principal, puede lanzar IOException
		int numeroPuerto = 6000; // Puerto en el que escuchará el servidor
		ServerSocket servidor = null; // Declaración del socket servidor

		servidor = new ServerSocket(numeroPuerto); // Se crea el servidor TCP que escucha en el puerto 6000

		Socket clienteConectado = null; // Socket para el cliente que se conectará
		System.out.println("Esperando al cliente....."); // Mensaje en consola para indicar espera
		clienteConectado = servidor.accept(); // El servidor se queda esperando a que un cliente se conecte

		// CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = null; // Flujo de salida básico
		salida = clienteConectado.getOutputStream(); // Obtiene el flujo de salida desde el socket del cliente
		DataOutputStream flujoSalida = new DataOutputStream(salida); // Permite enviar datos primitivos (int, float, etc.)

		// CREO FLUJO DE ENTRADA DE CLIENTE
		InputStream entrada = null; // Flujo de entrada básico
		entrada = clienteConectado.getInputStream(); // Obtiene el flujo de entrada desde el socket del cliente
		DataInputStream flujoEntrada = new DataInputStream(entrada); // Permite leer datos primitivos

		// EL CLIENTE ME ENVÍA UN MENSAJE (un número entero)
		Integer numero = flujoEntrada.readInt(); // Lee un entero enviado por el cliente
		System.out.print("Número enviado por el CLIENTE: " + numero); // Muestra el número recibido

		numero = numero * numero; // Calcula el cuadrado del número
		flujoSalida.writeInt(numero); // Envía el resultado de vuelta al cliente

		// CIERRE DE STREAMS Y SOCKETS
		entrada.close(); // Cierra el flujo de entrada básico
		flujoEntrada.close(); // Cierra el flujo de entrada con DataInputStream
		salida.close(); // Cierra el flujo de salida básico
		flujoSalida.close(); // Cierra el flujo de salida con DataOutputStream
		clienteConectado.close(); // Cierra el socket del cliente
		servidor.close(); // Cierra el socket del servidor
	}
}
