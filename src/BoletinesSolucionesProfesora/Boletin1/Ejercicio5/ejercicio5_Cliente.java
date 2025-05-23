package BoletinesSolucionesProfesora.Boletin1.Ejercicio5; // Define el paquete del ejercicio

/*
 * Boletín 1 SOCKETS
 * Ejercicio 5
 * Crea un programa servidor que pueda atender hasta 3 clientes. Debe enviar a cada cliente un
 * mensaje indicando el número de cliente que es. Este número será 1, 2 o 3. El cliente mostrará
 * el mensaje recibido. Cambia el programa para que lo haga para N clientes, siendo N un
 * parámetro que tendrás que definir en el programa.
 */

import java.io.*; // Para flujos de entrada/salida
import java.net.*; // Para clases de red: Socket, InetAddress, etc.

public class ejercicio5_Cliente { // Clase del cliente

    public static void main(String[] args) throws Exception { // Método principal, lanza excepción general
        String Host = "localhost"; // Dirección del servidor (en este caso, el mismo equipo)
        int Puerto = 6000; // Puerto al que se conecta (debe coincidir con el que use el servidor)

        System.out.println("Conectando con el servidor..."); // Mensaje informativo

        // ABRIR SOCKET
        Socket Cliente = null; // Se declara el socket

        try {
            Cliente = new Socket(Host, Puerto); // Intenta conectarse al servidor
        } catch (ConnectException c) { // Si no puede conectar (servidor no está activo)
            System.out.println("SERVIDOR CERRADO. "); // Mensaje de error
            return; // Termina el programa
        }

        // RECIBO SALUDO DEL SERVIDOR
        InputStream aux = Cliente.getInputStream(); // Obtiene el flujo de entrada del socket
        DataInputStream flujo = new DataInputStream(aux); // Permite leer tipos de datos primitivos (como UTF)

        // Lee el mensaje del servidor (ej. "Eres el cliente número 1")
        System.out.println("Recibiendo del servidor: " + flujo.readUTF());

        System.out.println("CLIENTE CERRADO. "); // Mensaje final

        Cliente.close(); // Cierra la conexión del socket
    }
}
