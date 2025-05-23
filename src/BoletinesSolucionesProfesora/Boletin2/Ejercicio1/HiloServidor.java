package BoletinesSolucionesProfesora.Boletin2.Ejercicio1; // Paquete del ejercicio

import java.io.DataInputStream; // Para leer datos primitivos (como UTF) desde el cliente
import java.io.EOFException; // Para detectar fin de transmisión del cliente
import java.io.IOException; // Excepciones de entrada/salida
import java.io.ObjectOutputStream; // Para enviar objetos serializables al cliente
import java.net.Socket; // Clase Socket para gestionar la conexión
import java.net.SocketException; // Para capturar errores de red

class HiloServidor extends Thread { // Clase que extiende Thread para gestionar un cliente en paralelo

    DataInputStream fentrada; // Flujo de entrada para recibir datos del cliente
    ObjectOutputStream fsalida; // Flujo de salida para enviar objetos al cliente
    Socket socket = null; // Socket asociado a este cliente
    Profesor arrayObjetosProfesor[]; // Array con los datos de todos los profesores
    Integer idcliente; // Identificador asignado a este cliente

    // Constructor del hilo
    public HiloServidor(Socket s, Profesor[] arrayObjetosProfesor, int idcliente) {
        socket = s; // Asocia el socket recibido
        this.arrayObjetosProfesor = arrayObjetosProfesor; // Almacena la lista de profesores
        this.idcliente = idcliente; // Guarda el ID del cliente

        try {
            // Inicializa los flujos de comunicación
            fsalida = new ObjectOutputStream(socket.getOutputStream()); // Flujo para enviar objetos
            fentrada = new DataInputStream(socket.getInputStream()); // Flujo para leer cadenas UTF
        } catch (IOException e) {
            System.out.println("ERROR DE E/S con cliente " + idcliente); // Si falla la conexión
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al iniciar el hilo
    public void run() {
        String cadena = ""; // Almacena el ID de profesor solicitado

        // Enviar al cliente su identificador
        try {
            fsalida.writeObject(idcliente);
        } catch (IOException e2) {
            System.out.println("ERROR AL ENVIAR IDCLIENTE CON CLIENTE " + idcliente);
            e2.printStackTrace();
        }

        // Bucle de atención al cliente hasta que escriba "*"
        while (!cadena.trim().equals("*")) {
            Profesor profesor;
            try {
                try {
                    cadena = fentrada.readUTF(); // Espera recibir un ID de profesor
                } catch (SocketException dd) { // Si el cliente se desconecta de forma abrupta
                    System.out.println("\tERROR AL LEER EL CLIENTE: " + idcliente);
                    break;
                } catch (EOFException EO) { // Si se detecta fin de flujo (cliente cerró conexión)
                    System.out.println("EL CLIENTE " + idcliente + " HA FINALIZADO ");
                    break;
                }

                System.out.println("\tConsultando id: " + cadena + ", solicitado por cliente: " + idcliente);

                int id = Integer.parseInt(cadena); // Convierte el ID a entero
                profesor = DatosProfesor(id); // Busca el profesor correspondiente

                fsalida.reset(); // Limpia la caché del flujo para evitar errores con la serialización
                fsalida.writeObject(profesor); // Envía el objeto Profesor al cliente

            } catch (IOException e) {
                e.printStackTrace(); // Muestra cualquier otro error
            }
        }

        // Al finalizar el bucle
        System.out.println("FIN CON: " + socket.toString() + " DEL CLIENTE:  " + idcliente);

        // Cierra flujos y socket
        try {
            fsalida.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para buscar un profesor por su ID
    private Profesor DatosProfesor(int identificador) {
        Especialidad noexiste = new Especialidad(0, "sin datos"); // Objeto por defecto si no se encuentra

        Profesor profesor = new Profesor(identificador, "No existe", null, noexiste); // Profesor vacío

        for (int i = 0; i < arrayObjetosProfesor.length; i++) {
            if (arrayObjetosProfesor[i].getIdprofesor() == identificador) // Busca coincidencia
                profesor = arrayObjetosProfesor[i]; // Si encuentra, lo guarda
        }
        return profesor; // Devuelve el profesor encontrado o uno vacío
    }
}
