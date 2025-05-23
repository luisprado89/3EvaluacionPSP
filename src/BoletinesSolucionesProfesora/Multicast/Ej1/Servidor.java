package BoletinesSolucionesProfesora.Multicast.Ej1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *              EJERCICIO MULTICAST
 * Implementar un programa que actúe como servidor multicast. Dicho servidor,
 * recogerá de teclado cadenas de texto que introducirá el usuario por teclado y las irá enviando a todos
 * los clientes conectados al grupo multicast.
 *
 * El programa cliente, solicitará al usuario su nombre y lo enviará al grupo, indicando que se ha conectado.
 * Cada cliente además recibirá todos los mensajes asignados al grupo.
 * */

public class Servidor {
    public static void main(String[] args) {

        // FLUJO PARA LEER DATOS DESDE TECLADO
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        MulticastSocket ms = null;

        try {
            // 1. CREACIÓN DEL SOCKET MULTICAST (solo para enviar en este caso)
            ms = new MulticastSocket(); // No se especifica puerto porque solo se enviará

            int Puerto = 12345; // Puerto multicast al que deben suscribirse los clientes
            InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Dirección del grupo multicast (clase D)

            String cadena = "";

            // 2. BUCLE PRINCIPAL: LEER Y ENVIAR MENSAJES
            while (!cadena.trim().equals("*")) { // Termina si el usuario escribe '*'
                System.out.print("Datos a enviar al grupo: ");
                cadena = in.readLine(); // Lee el mensaje desde teclado

                // 3. CONSTRUIR Y ENVIAR PAQUETE MULTICAST
                DatagramPacket paquete = new DatagramPacket(
                        cadena.getBytes(),        // Contenido del mensaje
                        cadena.length(),          // Longitud del mensaje
                        grupo,                    // Dirección IP del grupo
                        Puerto                    // Puerto del grupo
                );
                ms.send(paquete); // Envía el paquete al grupo multicast
            }

            // 4. CERRAR SOCKET CUANDO SE TERMINA
            ms.close();
            System.out.println("Socket cerrado...");

        } catch (IOException e) {
            throw new RuntimeException(e); // Manejo simple de excepciones
        }
    }
}
