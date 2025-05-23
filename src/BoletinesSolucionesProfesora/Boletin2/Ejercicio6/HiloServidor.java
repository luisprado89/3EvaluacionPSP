package BoletinesSolucionesProfesora.Boletin2.Ejercicio6;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
    DataInputStream fentrada;
    Socket socket = null;
    DataOutputStream fsalida = null;

    public HiloServidor(Socket s) {
        socket = s;
        try {
            // CREO FLUJO DE entrada para leer los mensajes
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }
    }// ..

    public void run() {

        InetAddress direccion = socket.getInetAddress();
        System.out.println("=>Conecta IP " + direccion +
                ", Puerto remoto: " + socket.getPort() + socket.toString());


        while (true) {
            String cadena = "";
            try {
                cadena = fentrada.readUTF();
                if (cadena.trim().equals("*")) break;

                String mayuscula = cadena.toUpperCase();
                fsalida.writeUTF(mayuscula);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }// fin while

        try {
            socket.close();
            System.out.println("\t=>Desconecta IP " + direccion +
                    ", Puerto remoto: " + socket.getPort());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//run
}// ..HiloServidor