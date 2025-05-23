package BoletinesSolucionesProfesora.Boletin2.Ejercicio2;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ejercicio2_Servidor {

    public static void main(String[] args) throws IOException {
        byte[] bufer = new byte[1024];//para recibir el datagrama

        //Asocio el socket al puerto 12345
        DatagramSocket socket = new DatagramSocket(12345);

        System.out.println("Servidor Esperando Datagrama .......... ");
        DatagramPacket recibo;

        String paquete = "";
        do {
            bufer = new byte[1024];
            recibo = new DatagramPacket(bufer, bufer.length);

            socket.receive(recibo);//recibo datagrama

            paquete = new String(recibo.getData());//obtengo String
            System.out.println("Servidor Recibe:" + paquete);

            if (paquete.trim().equals("*")) break;

            // DIRECCION ORIGEN DEL MENSAJE
            InetAddress IPOrigen = recibo.getAddress();
            int puerto = recibo.getPort();

            //pasar a mayusculas y enviarlo al cliente
            // ENVIANDO DATAGRAMA AL CLIENTE - cadena a mayuscula
            String mayuscula = paquete.trim().toUpperCase();
            byte[] enviados = new byte[1024];
            enviados = mayuscula.getBytes();

            DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            socket.send(envio);

        } while (!paquete.trim().equals("*"));


        // CERRAR STREAMS Y SOCKETS
        System.out.println("Cerrando conexion...");
        socket.close();

    }

}
