package BoletinesSolucionesProfesora.Boletin2.Ejercicio5;
/*
 * Boletin 2 SOCKETS
 * Ejercicio 5
 * 5. Crea una clase de nombre Curso, con los siguientes atributos:
 * String id;
 * String descripcion;
 * Crea otra clase de nombre Alumno, con los siguientes atributos:

 * String idalumno;
 * String nombre;
 * Curso curso;
 *
 * int nota;
 * Crea en las clases anteriores los constructores y métodos get y set necesarios.

 * Utilizando sockets UDP crea un programa servidor que inicialice un array de 5 objetos de tipo
 * Alumno.
 * El servidor, en un bucle infinito, solicitará al cliente un idAlumno y le devolverá el objeto
 * Alumno que corresponda.
 * Crea un programa cliente en el que se introduzca por teclado el idAlumno que se desea
 * consultar. Dicho programa recogerá datos hasta recibir un * por parte del usuario.
 * Si el idAlumno no se encuentra registrado, el servidor le devolverá un objeto Alumno con datos
 * vacíos.
 */

import java.io.*;
import java.net.*;

public class clienteUDP {

	public static void main(String args[]) throws Exception {

		DatagramSocket clientSocket = new DatagramSocket(); // Socket UDP para el cliente

		// Entrada desde teclado
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Dirección y puerto del servidor
		InetAddress IPServidor = InetAddress.getLocalHost(); // IP del servidor (localhost)
		int puerto = 9876; // Puerto UDP del servidor

		while (true) {
			System.out.print("Introduce identificador a consultar: ");
			String cadena = in.readLine(); // Lee ID alumno o '*'

			if (cadena.trim().equals("*")) break; // Si es '*', termina

			// SERIALIZACIÓN: convertir el String (ID alumno) a bytes
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bs);
			os.writeObject(cadena); // Envía el identificador como objeto
			os.close();
			byte[] enviados = bs.toByteArray(); // bytes listos para enviar

			// ENVÍA EL DATAGRAMA AL SERVIDOR
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
			clientSocket.send(envio);

			// PREPARA PARA RECIBIR RESPUESTA DEL SERVIDOR
			byte[] recibidos = new byte[1024];
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);

			try {
				clientSocket.setSoTimeout(5000); // Espera máxima: 5 segundos
				clientSocket.receive(recibo); // Espera la respuesta

				// DESERIALIZACIÓN: convertir byte[] recibido en objeto Alumno
				ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
				ObjectInputStream is = new ObjectInputStream(bais);
				Alumno alumno = (Alumno) is.readObject(); // Reconstruye el objeto Alumno
				is.close();

				// MUESTRA LOS DATOS DEL ALUMNO RECIBIDO
				System.out.printf("\tNombre: %s, Curso: %s - %s, Nota: %d %n",
						alumno.getNombre(),
						alumno.getCurso().getId(),
						alumno.getCurso().getDescripcion(),
						alumno.getNota());

			} catch (InterruptedIOException ii) {
				// Si no se recibe respuesta dentro del tiempo límite
				System.out.println("\t2<<FINALIZADO TIEMPO DE ESPERA - PAQUETE PERDIDO>>");
			}
		}

		clientSocket.close(); // Cierra el socket UDP
		System.out.print("Fin de cliente... ");
	}
}