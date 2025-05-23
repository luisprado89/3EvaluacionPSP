package BoletinesSolucionesProfesora.Boletin2.Ejercicio3;
/*
* Boletin 2 SOCKETS
* Ejercicio 3
* 3. Crea una clase Java llamada Numeros que defina 3 atributos , uno de ellos entero, y los otros
* 2 de tipo long:
* int numero;
* long cuadrado;
* long cubo;
*  Define un constructor con parámetros y otro sin parámetros. Define los métodos get y set de
* los atributos. Crea un programa cliente que introduzca por teclado un número e inicialice un
* objeto Numeros, el atributo numero debe contener el número introducido por teclado. Debe
* enviar ese objeto al programa servidor. El proceso se repetirá mientras el número introducido
* por teclado sea mayor que 0.
*
* Crea un programa servidor que reciba un objeto Numeros. Debe calcular el cuadrado y el cubo
* del atributo numero y debe enviar el objeto al cliente con los cálculos realizados, el cuadrado
* y el cubo en sus atributos respectivos. El cliente recibirá el objeto y visualizará el cuadrado y
* el cubo del número introducido por teclado. El programa servidor finalizará cuando el número
* recibido en el objeto Numeros sea menor o igual que 0.
*
* Controlar posibles errores, por ejemplo si ejecutamos el cliente y el servidor no está iniciado,
* o si estando el servidor ejecutándose ocurre algún error en el cliente, o este finaliza
* inesperadamente, etc.
 */

import java.io.ByteArrayInputStream; // Para convertir byte[] a objeto
import java.io.ByteArrayOutputStream; // Para convertir objeto a byte[]
import java.io.IOException;
import java.io.ObjectInputStream; // Para leer un objeto de un byte[]
import java.io.ObjectOutputStream; // Para escribir un objeto a un byte[]
import java.net.ConnectException;
import java.net.DatagramPacket; // Paquetes UDP
import java.net.DatagramSocket; // Socket UDP
import java.net.InetAddress; // IP destino
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente_UDP {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		int Puerto = 6000; // Puerto del servidor
		InetAddress IPServidor = InetAddress.getLocalHost(); // IP del servidor (localhost)

		Scanner sc = new Scanner(System.in); // Scanner para leer desde teclado
		DatagramSocket cliente = null;

		try {
			cliente = new DatagramSocket(); // Crea el socket UDP
			System.out.println("PROGRAMA CLIENTE INICIADO....");
		} catch (ConnectException ce) {
			System.out.println("ERROR AL ESTABLECER LA CONEXIÓN CON EL SERVIDOR....");
			System.exit(0);
		}

		int numero = 0;

		do {
			System.out.print("Introduce un numero: ");
			try {
				numero = sc.nextInt(); // Lee el número desde teclado
				sc.nextLine(); // Limpia el buffer
			} catch (InputMismatchException nn) {
				sc.nextLine(); // Descarta entrada inválida
				numero = 1; // Valor válido para seguir
				System.out.print("\tNúmero incorrecto...\n");
				continue;
			}

			// Crea el objeto y establece el número
			Numeros n = new Numeros();
			n.setNumero(numero);

			// CONVERTIR OBJETO A BYTE[]
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bs);
			out.reset(); // Limpia posibles referencias internas
			out.writeObject(n); // Serializa el objeto
			byte[] enviados = bs.toByteArray(); // Convierte a byte[]

			// Enviar datagrama UDP al servidor
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, Puerto);
			cliente.send(envio);

			// Si el número es mayor a 0, esperar la respuesta
			if (numero > 0) {
				byte[] recibidos = new byte[1024]; // Buffer de entrada
				DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
				cliente.receive(paqRecibido); // Recibe el datagrama

				// CONVERTIR BYTE[] A OBJETO
				ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
				ObjectInputStream in = new ObjectInputStream(bais);

				Numeros dato = (Numeros) in.readObject(); // Reconstruye el objeto recibido
				in.close();

				// Mostrar resultados
				System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
			}

		} while (numero > 0); // Repetir mientras el número sea mayor que 0

		System.out.println("CLIENTE UDP FINALIZADO....");

		// CERRAR SOCKET
		cliente.close();
	}
}