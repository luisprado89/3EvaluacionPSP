package BoletinesSolucionesProfesora.Boletin1.Ejercicio4;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Boletín 1 SOCKETS
 * Ejercicio 4
 * Crea un programa cliente que introduzca por teclado un número entero
 * y se lo envíe al servidor. El servidor le devolverá el cuadrado del número.
 * */


public class ejercicio4_Cliente {

	public static void main(String[] args) throws IOException {
		String Host = "localhost";
		int Puerto = 6000;// puerto remoto
		Socket Cliente = new Socket(Host, Puerto);

		// CREO FLUJO DE SALIDA AL SERVIDOR
		OutputStream salida = null;
		salida = Cliente.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);

		// CREO FLUJO DE ENTRADA AL SERVIDOR
		InputStream fentrada = null;
		fentrada = Cliente.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(fentrada);

		// ENTRADA ESTANDAR
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce un número: ");
		Integer n;
		try {
			n = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.print("Número introducido incorrecto.");
			Cliente.close();
			return;
		}
		
		/*
		 * 		String cadena = sc.nextLine();
		Integer n;
		try {
			n = Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			System.out.print("Número introducido incorrecto.");
			Cliente.close();
			return;
		}
		 * */

		flujoSalida.writeInt(n);
		
		int cuadrado = flujoEntrada.readInt();
		
		System.out.println("EL CUADRADO ES => " + cuadrado);

		flujoSalida.close();
		flujoEntrada.close();
		System.out.println("Fin de proceso... ");

		Cliente.close();
	}//

}
