package BoletinesSolucionesProfesora.Multicast.Ej1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Se crea el socket multicast
        int Puerto = 12345;//Puerto multicast
        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket(Puerto);

            InetAddress grupo = InetAddress.getByName("225.0.0.1"); //Grupo
            SocketAddress sock = new InetSocketAddress(grupo, Puerto);
            ms.joinGroup(sock, NetworkInterface.getByInetAddress(grupo));// Nos unimos al grupo

            System.out.print("�Bienvenido! �C�mo te llamas?: ");
            String cadena = "Se ha conectado " + sc.nextLine();
            // ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket (cadena.getBytes(), cadena.length(), grupo, Puerto);
            ms.send(paquete);

            String msg = "";

            while (!msg.trim().equals("*")) {
                byte[] buf = new byte[1000];
                //Recibe el paquete del servidor multicast
                paquete = new DatagramPacket(buf, buf.length);
                ms.receive(paquete);

                msg = new String(paquete.getData());
                System.out.println("Recibo: " + msg.trim());
            }
            // Mismos par�metros que join
            ms.leaveGroup(sock, NetworkInterface.getByInetAddress(grupo));
            ms.close(); //cierra socket
            System.out.println("Socket cerrado...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

