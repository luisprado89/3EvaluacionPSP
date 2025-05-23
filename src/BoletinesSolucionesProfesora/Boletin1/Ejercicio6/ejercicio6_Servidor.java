package BoletinesSolucionesProfesora.Boletin1.Ejercicio6;

import java.io.*;
import java.net.*;

public class ejercicio6_Servidor {
	public static void main(String[] arg) throws IOException {
		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		String cad = "";

		System.out.println("Esperando conexion...");
		Socket cliente = servidor.accept();
		System.out.println("Cliente conectado...");

		// CREO FLUJO DE SALIDA AL SERVIDOR
		OutputStream salida = null;
		salida = cliente.getOutputStream();
		DataOutputStream fsalida = new DataOutputStream(salida);

		// CREO FLUJO DE ENTRADA DE SERVIDOR
		InputStream entrada = null;
		entrada = cliente.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		cad = flujoEntrada.readUTF();
		while (!cad.equals("*")) {
			Integer n = cad.length();
			System.out.println("Recibiendo: " + cad + " enviando: " + n);
			fsalida.writeInt(n);
			cad = flujoEntrada.readUTF();
		}
		// CERRAR STREAMS Y SOCKETS
		System.out.println("Cerrando conexion...");

		flujoEntrada.close();
		fsalida.close();
		cliente.close();
		servidor.close();
	}// main
}// fin