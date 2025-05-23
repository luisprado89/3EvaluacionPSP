package BoletinesSolucionesProfesora.Multicast.Ej1; // Paquete del ejercicio

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Para leer el nombre del usuario

        int Puerto = 12345; // Puerto del grupo multicast
        MulticastSocket ms = null;

        try {
            // 1. CREAR SOCKET MULTICAST Y UNIRSE AL GRUPO
            ms = new MulticastSocket(Puerto); // Escucha en el puerto del grupo
            InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Dirección multicast (clase D)
            SocketAddress sock = new InetSocketAddress(grupo, Puerto); // Dirección completa (IP+puerto)

            // Se une al grupo utilizando la interfaz de red asociada a la IP del grupo
            ms.joinGroup(sock, NetworkInterface.getByInetAddress(grupo));

            // 2. PEDIR NOMBRE DEL USUARIO Y ENVIAR MENSAJE DE CONEXIÓN
            System.out.print("¡Bienvenido! ¿Cómo te llamas?: ");
            String cadena = "Se ha conectado " + sc.nextLine(); // Mensaje de conexión
            DatagramPacket paquete = new DatagramPacket(
                    cadena.getBytes(), cadena.length(), grupo, Puerto
            );
            ms.send(paquete); // Envia el paquete al grupo multicast

            // 3. BUCLE DE RECEPCIÓN DE MENSAJES
            String msg = "";
            while (!msg.trim().equals("*")) {
                byte[] buf = new byte[1000]; // Buffer para recibir mensajes
                paquete = new DatagramPacket(buf, buf.length); // Preparar paquete para recibir
                ms.receive(paquete); // Espera y recibe un paquete multicast

                msg = new String(paquete.getData()); // Convierte los bytes a String
                System.out.println("Recibo: " + msg.trim()); // Muestra mensaje recibido
            }

            // 4. SALIR DEL GRUPO MULTICAST
            ms.leaveGroup(sock, NetworkInterface.getByInetAddress(grupo)); // Salida del grupo
            ms.close(); // Cierra el socket
            System.out.println("Socket cerrado...");

        } catch (IOException e) {
            throw new RuntimeException(e); // Muestra cualquier error de E/S
        }
    }
}
