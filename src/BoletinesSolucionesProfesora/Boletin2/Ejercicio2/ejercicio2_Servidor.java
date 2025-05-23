package BoletinesSolucionesProfesora.Boletin2.Ejercicio2; // Paquete del ejercicio

import java.io.IOException; // Excepciones de entrada/salida
import java.net.DatagramPacket; // Representa un datagrama UDP (mensaje)
import java.net.DatagramSocket; // Socket para comunicación UDP
import java.net.InetAddress; // Representa direcciones IP

public class ejercicio2_Servidor { // Clase principal del servidor UDP

    public static void main(String[] args) throws IOException {
        byte[] bufer = new byte[1024]; // Buffer para recibir los datos del cliente

        // Crea el socket y lo asocia al puerto 12345
        DatagramSocket socket = new DatagramSocket(12345);

        System.out.println("Servidor Esperando Datagrama .......... ");
        DatagramPacket recibo; // Objeto para recibir datagramas

        String paquete = ""; // Almacenará la cadena recibida
        do {
            bufer = new byte[1024]; // Reinicia el buffer en cada iteración
            recibo = new DatagramPacket(bufer, bufer.length); // Crea el paquete de recepción

            socket.receive(recibo); // Espera a recibir un datagrama (bloqueante)

            paquete = new String(recibo.getData()); // Convierte el contenido del datagrama a String
            System.out.println("Servidor Recibe: " + paquete); // Muestra el mensaje recibido

            // Si el cliente envía "*", se termina el bucle
            if (paquete.trim().equals("*")) break;

            // Obtiene dirección IP y puerto del cliente que envió el mensaje
            InetAddress IPOrigen = recibo.getAddress();
            int puerto = recibo.getPort();

            // Convierte el texto a mayúsculas
            String mayuscula = paquete.trim().toUpperCase();

            // Prepara el mensaje de respuesta
            byte[] enviados = new byte[1024];
            enviados = mayuscula.getBytes(); // Convierte a bytes

            // Crea el paquete de respuesta dirigido al cliente
            DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            socket.send(envio); // Envía el datagrama de respuesta

        } while (!paquete.trim().equals("*")); // Repite hasta recibir "*"

        // CIERRE DEL SOCKET
        System.out.println("Cerrando conexion...");
        socket.close(); // Cierra el socket
    }
}
