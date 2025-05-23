package BoletinesSolucionesProfesora.Boletin1;
/*
 * Boletín 1 SOCKETS
 * Ejercicio 1
 * Realiza un programa Java que admita desde consola nombres de máquinas o direcciones IP y
 *vaya mostrando por pantalla información sobre ellas, haciendo uso de la clase InetAddress.
 */
import java.net.*;
import java.util.Scanner;

public class Ejercicio1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String maquina = "";
		InetAddress dir = null;

		while (!maquina.equals("*")) {
			System.out.println("Introduzca una IP:");
			maquina = sc.nextLine();

			if (!maquina.equals("*")) {
				try {
					dir = InetAddress.getByName(maquina);
					pruebaMetodos(dir);
				} catch (UnknownHostException e) {
					System.out.println("HOST DESCONOCIDO");
					System.out.println(e.getMessage());
					System.exit(0);
				}

				// Array de tipo InetAddress con todas las direcciones IP asignadas //
				try {
					InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
					for (int i = 0; i < direcciones.length; i++)
						System.out.println(direcciones[i].toString());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}// main

	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName():  " + dir);
		InetAddress dir2;
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// USAMOS METODOS DE LA CLASE
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}// pruebaMetodos
}//