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
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Se crea el socket multicast.
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket();

            int Puerto = 12345;//Puerto multicast
            InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo

            String cadena = "";

            while (!cadena.trim().equals("*")) {
                System.out.print("Datos a enviar al grupo: ");
                cadena = in.readLine();
                // ENVIANDO AL GRUPO
                DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, Puerto);
                ms.send(paquete);
            }
            ms.close();//cierro socket
            System.out.println("Socket cerrado...");

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
