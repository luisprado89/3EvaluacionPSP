package BoletinesSolucionesProfesora.Boletin1.Ejercicio6;
/*
 * Boletín 1 SOCKETS
 * Ejercicio 6
 * Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta
 * introducir un asterisco. Las cadenas se enviarán a un programa servidor. El programa servidor
 * aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente el
 * número de caracteres de la misma. El programa servidor finalizará cuando reciba un asterisco
 * como cadena.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ejercicio6_Cliente {
  public static void main(String[] args) throws IOException {
	String host = "localhost";
	int puerto = 6000;// puerto remoto
	Socket cliente = new Socket(host, puerto);
		
	// CREO FLUJO DE SALIDA AL SERVIDOR	
	OutputStream salida = null;
	salida = cliente.getOutputStream();
	DataOutputStream fsalida = new DataOutputStream(salida);
			 
	// CREO FLUJO DE ENTRADA DE SERVIDOR
	InputStream entrada = null;
	entrada = cliente.getInputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);
	
	String cadena;
	Scanner sc = new Scanner(System.in);
	System.out.print("Introduce cadena: ");
	cadena = sc.nextLine();
	while(!cadena.equals("*")){	
		fsalida.writeUTF(cadena);	
		Integer longitud = flujoEntrada.readInt();
		System.out.print("\tNumero de caracteres: "+longitud);
		System.out.println("Introduce cadena: ");
		cadena = sc.nextLine();
	} 
	fsalida.writeUTF("*");	
	fsalida.close();
	flujoEntrada.close();
	System.out.println("Fin de proceso... ");
	cliente.close();
	}//
}//
