package BoletinesSolucionesProfesora.Boletin2.Ejercicio4;
/*
 * Boletin 2 SOCKETS
 * Ejercicio 4
 * 4. Usando sockets UDP, realiza un programa servidor que espere un datagrama de un cliente. El
 * cliente le envía un objeto Persona que previamente había inicializado. El servidor modifica los
 * datos del objeto Persona y se lo envía de vuelta al cliente. Visualiza los datos del objeto
 * Persona tanto en el programa cliente cuando los envía y los recibe como en el programa
 * servidor cuando los recibe y los envía modificados.
 */

import java.io.*;
import java.net.*;

public class ejercicio4_cliente {

	public static void main(String args[]) throws Exception {

		// SOCKET cliente UDP
		DatagramSocket clientSocket = new DatagramSocket();

		byte[] recibidos = new byte[1024]; // Buffer para la respuesta del servidor

		// DATOS DEL SERVIDOR
		InetAddress IPServidor = InetAddress.getLocalHost(); // Dirección IP (localhost)
		int puerto = 9876; // Puerto donde escucha el servidor

		// CREA OBJETO PERSONA
		Persona per = new Persona("Maria", 22); // Objeto que se va a enviar

		// CONVERTIR OBJETO A BYTES
		ByteArrayOutputStream bs = new ByteArrayOutputStream(); // Flujo de salida en memoria
		ObjectOutputStream os = new ObjectOutputStream(bs); // Flujo para escribir objetos
		os.writeObject(per); // Serializa el objeto
		os.close(); // Cierra el flujo de objeto
		byte[] bytes = bs.toByteArray(); // Obtiene los bytes del objeto

		// ENVÍA DATAGRAMA AL SERVIDOR
		System.out.println("Enviando " + bytes.length + " bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPServidor, puerto);
		clientSocket.send(envio); // Envía el objeto serializado

		// ESPERA RESPUESTA DEL SERVIDOR
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama....");
		clientSocket.receive(recibo); // Bloquea hasta recibir respuesta

		// CONVERTIR LOS BYTES RECIBIDOS A OBJETO
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); // Flujo desde los bytes
		ObjectInputStream is = new ObjectInputStream(bais); // Flujo para leer el objeto
		Persona persona = (Persona) is.readObject(); // Reconstruye el objeto Persona
		is.close(); // Cierra el flujo

		// MUESTRA LOS DATOS MODIFICADOS DEL OBJETO
		InetAddress IPOrigen = recibo.getAddress(); // Dirección del servidor que respondió
		int puertoOrigen = recibo.getPort(); // Puerto del servidor
		System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
		System.out.println("\tDatos: " + persona.getNombre() + "*" + persona.getEdad());

		clientSocket.close(); // Cierra el socket cliente
	}
}