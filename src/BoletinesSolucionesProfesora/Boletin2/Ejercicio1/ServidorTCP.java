package BoletinesSolucionesProfesora.Boletin2.Ejercicio1; // Paquete del ejercicio

import java.io.IOException; // Para capturar errores de E/S
import java.net.ServerSocket; // Para crear el socket servidor
import java.net.Socket; // Para aceptar conexiones cliente

public class ServidorTCP { // Clase principal del servidor

	static Profesor arrayObjetosProfesor[] = new Profesor[5]; // Array con 5 objetos Profesor

	public static void main(String[] args) throws IOException {
		int idcliente = 0; // Contador para asignar IDs únicos a cada cliente

		llenarArrays(); // Llama al método que inicializa los datos de los profesores

		ServerSocket servidor; // Objeto para escuchar conexiones entrantes
		servidor = new ServerSocket(6000); // Abre el servidor en el puerto 6000
		System.out.println("Servidor iniciado...");

		while (true) { // Bucle infinito para aceptar múltiples clientes
			Socket cliente; // Socket para la conexión con un cliente
			cliente = servidor.accept(); // Espera una conexión cliente (bloqueante)
			idcliente++; // Incrementa el número de cliente

			System.out.println("Cliente " + idcliente + " conectado");

			// Crea un nuevo hilo para atender al cliente y lo arranca
			HiloServidor hilo = new HiloServidor(cliente, arrayObjetosProfesor, idcliente);
			hilo.start(); // Inicia el hilo
		}
	}

	// Método que inicializa el array de profesores con datos de ejemplo
	private static void llenarArrays() {
		// Creación de asignaturas
		Asignatura asi1 = new Asignatura(1, "Matematicas I");
		Asignatura asi11 = new Asignatura(11, "Matematicas II");
		Asignatura asi12 = new Asignatura(12, "Matematicas III");

		Asignatura asi2 = new Asignatura(2, "ADAT");
		Asignatura asi3 = new Asignatura(3, "PSP");
		Asignatura asi4 = new Asignatura(4, "PMD");
		Asignatura asi6 = new Asignatura(6, "PROGRAMACION");
		Asignatura asi7 = new Asignatura(7, "AWEB");
		Asignatura asi8 = new Asignatura(8, "LMARCAS");
		Asignatura asi9 = new Asignatura(9, "ENTORNOS");
		Asignatura asi10 = new Asignatura(10, "BD");

		Asignatura asi21 = new Asignatura(21, "Lengua");
		Asignatura asi22 = new Asignatura(22, "Ingles");
		Asignatura asi23 = new Asignatura(23, "Frances");

		// Creación de especialidades
		Especialidad espe1 = new Especialidad(1, "INFORMATICA");
		Especialidad espe2 = new Especialidad(2, "MATEMATICAS");
		Especialidad espe3 = new Especialidad(3, "LENGUA");

		// Profesores con sus asignaturas y especialidades

		Asignatura[] asignaturas1 = new Asignatura[3];
		asignaturas1[0] = asi1;
		asignaturas1[1] = asi11;
		asignaturas1[2] = asi12;
		Profesor profesor2 = new Profesor(2, "Luis", asignaturas1, espe2);
		Profesor profesor5 = new Profesor(5, "Jesus", asignaturas1, espe2);

		Asignatura[] asignaturas2 = new Asignatura[3];
		asignaturas2[0] = asi2;
		asignaturas2[1] = asi3;
		asignaturas2[2] = asi4;
		Profesor profesor1 = new Profesor(1, "María Jesús", asignaturas2, espe1);

		Asignatura[] asignaturas3 = new Asignatura[3];
		asignaturas3[0] = asi6;
		asignaturas3[1] = asi7;
		asignaturas3[2] = asi8;
		Profesor profesor4 = new Profesor(4, "Pedro", asignaturas3, espe1);

		Asignatura[] asignaturas4 = new Asignatura[3];
		asignaturas4[0] = asi21;
		asignaturas4[1] = asi22;
		asignaturas4[2] = asi23;
		Profesor profesor3 = new Profesor(3, "Maria", asignaturas4, espe3);

		// Almacena los profesores en el array global
		arrayObjetosProfesor[0] = profesor1;
		arrayObjetosProfesor[1] = profesor2;
		arrayObjetosProfesor[2] = profesor3;
		arrayObjetosProfesor[3] = profesor4;
		arrayObjetosProfesor[4] = profesor5;
	}
}
