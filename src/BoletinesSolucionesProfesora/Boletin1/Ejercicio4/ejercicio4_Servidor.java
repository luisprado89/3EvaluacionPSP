package BoletinesSolucionesProfesora.Boletin1.Ejercicio4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ejercicio4_Servidor {

	public static void main(String[] args) throws IOException {
		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = null;

		servidor = new ServerSocket(numeroPuerto);

		Socket clienteConectado = null;
		System.out.println("Esperando al cliente.....");
		clienteConectado = servidor.accept();

		// CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = null;
		salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);

		// CREO FLUJO DE ENTRADA DE CLIENTE
		InputStream entrada = null;
		entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		// EL CLIENTE ME ENVIA UN MENSAJE
		
		Integer numero = flujoEntrada.readInt();
		System.out.print("Número enviado por el CLIENTE: "+ numero);
		
		numero = numero * numero;
		flujoSalida.writeInt(numero);

		// CERRAR STREAMS Y SOCKETS
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		clienteConectado.close();
		servidor.close();

	}

}
