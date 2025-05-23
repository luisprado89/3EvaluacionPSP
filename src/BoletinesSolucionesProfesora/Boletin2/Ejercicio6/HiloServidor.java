package BoletinesSolucionesProfesora.Boletin2.Ejercicio6; // Paquete del ejercicio

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread { // Hereda de Thread para permitir ejecución concurrente

    DataInputStream fentrada; // Flujo de entrada para leer del cliente
    DataOutputStream fsalida; // Flujo de salida para escribir al cliente
    Socket socket = null;     // Socket para el cliente conectado

    // Constructor que recibe el socket del cliente
    public HiloServidor(Socket s) {
        socket = s;
        try {
            // Inicializa los flujos de entrada y salida
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }
    }

    // Método que se ejecuta cuando el hilo comienza
    public void run() {

        // Obtiene y muestra la IP del cliente conectado
        InetAddress direccion = socket.getInetAddress();
        System.out.println("=>Conecta IP " + direccion +
                ", Puerto remoto: " + socket.getPort() + socket.toString());

        while (true) {
            String cadena = "";
            try {
                cadena = fentrada.readUTF(); // Lee cadena del cliente
                if (cadena.trim().equals("*")) break; // Si es '*', termina conexión

                String mayuscula = cadena.toUpperCase(); // Convierte a mayúsculas
                fsalida.writeUTF(mayuscula); // Envía respuesta al cliente

            } catch (Exception e) {
                // Si ocurre un error (por ejemplo, el cliente se desconecta bruscamente)
                e.printStackTrace();
                break; // Sale del bucle
            }
        }

        try {
            socket.close(); // Cierra el socket del cliente
            System.out.println("\t=>Desconecta IP " + direccion +
                    ", Puerto remoto: " + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
