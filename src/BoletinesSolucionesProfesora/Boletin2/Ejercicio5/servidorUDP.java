package BoletinesSolucionesProfesora.Boletin2.Ejercicio5; // Paquete del ejercicio

import java.io.*;
import java.net.*;

public class servidorUDP {

	static Alumno arrayObjetos[] = new Alumno[5]; // Array de 5 alumnos

	public static void main(String args[]) throws Exception {

		// 1. CREAR CURSOS Y ALUMNOS
		Curso dam1 = new Curso("1", "Primero de CFGS DAM");
		Curso dam2 = new Curso("2", "Segundo de DAM");

		// Inicializamos los objetos alumno
		arrayObjetos[0] = new Alumno("20", "Fernando", dam1, 6);
		arrayObjetos[1] = new Alumno("32", "Epi", dam2, 4);
		arrayObjetos[2] = new Alumno("1", "Blas", dam2, 8);
		arrayObjetos[3] = new Alumno("25", "Manuela", dam1, 6);
		arrayObjetos[4] = new Alumno("4", "Alicia", dam2, 4);

		// 2. INICIAR SOCKET UDP
		DatagramSocket serverSocket = new DatagramSocket(9876); // Puerto del servidor

		// 3. BUCLE INFINITO PARA ATENDER CLIENTES
		while (true) {
			System.out.println("Servidor Esperando identificador.....");

			// Buffers para recepción y envío
			byte[] recibidos = new byte[1024];
			byte[] enviados = new byte[1024];

			// 4. RECIBIR PETICIÓN DEL CLIENTE
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido); // Espera datagrama

			// 5. CONVERTIR DATOS RECIBIDOS A OBJETO (String ID)
			ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
			ObjectInputStream in = new ObjectInputStream(bais);
			String identificador = (String) in.readObject(); // Extrae el ID alumno
			in.close();

			// 6. OBTENER DIRECCIÓN DEL CLIENTE PARA RESPONDER
			InetAddress IPOrigen = paqRecibido.getAddress(); // Dirección del cliente
			int puerto = paqRecibido.getPort(); // Puerto del cliente

			System.out.println("\tConsultando id: " + identificador);

			// 7. BUSCAR ALUMNO EN EL ARRAY
			Alumno alum = DatosAlumno(identificador); // Si no se encuentra, devuelve alumno con "No existe"

			// 8. CONVERTIR OBJETO ALUMNO A BYTES
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bs);
			out.writeObject(alum); // Serializa el objeto
			out.close();
			enviados = bs.toByteArray(); // Convierte el objeto a array de bytes

			// 9. ENVIAR RESPUESTA AL CLIENTE
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado); // Envía objeto alumno al cliente
		}

		// serverSocket.close(); // No se cierra porque el servidor está en bucle infinito
		// System.out.println("Socket cerrado...");
	}

	// Método auxiliar que devuelve el alumno con el ID buscado o un alumno vacío si no existe
	private static Alumno DatosAlumno(String identificador) {
		Curso noexiste = new Curso("*", "Sin datos"); // Curso por defecto
		Alumno al = new Alumno(identificador, "No existe", noexiste, -1); // Alumno por defecto
		for (int i = 0; i < arrayObjetos.length; i++) {
			if (arrayObjetos[i].getIdalumno().equals(identificador)) {
				al = arrayObjetos[i]; // Si encuentra coincidencia, devuelve el alumno real
			}
		}
		return al;
	}
}
