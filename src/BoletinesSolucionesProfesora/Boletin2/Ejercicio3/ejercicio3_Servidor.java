package BoletinesSolucionesProfesora.Boletin2.Ejercicio3;

import java.io.*;
import java.net.*;

public class ejercicio3_Servidor {
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);

        System.out.println("Esperando al cliente.....");
        Socket cliente = servidor.accept();

        // Se prepara un flujo de salida para objetos
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        // Se obtiene un stream para leer objetos
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());

        Numeros dato = new Numeros();
        try {
            dato = (Numeros) inObjeto.readObject();
        } catch (SocketException ce) {
            System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE...." + ce.getMessage());
            System.exit(0);
        }
        while (dato.getNumero() > 0) {
            long cuadrado = dato.getNumero() * dato.getNumero();
            long cubo = cuadrado * dato.getNumero();

            dato.setCubo(cubo);
            dato.setCuadrado(cuadrado);

            System.out.println("Recibo: " + dato.getNumero());
            outObjeto.writeObject(dato); // enviando objeto

            // OPCIONAL
            try {
                dato = (Numeros) inObjeto.readObject();
            } catch (SocketException ce) {
                System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE...." + ce.getMessage());
                System.exit(0);
            }
        }

        System.out.println("SERVIDOR FINALIZADO....");

        // CERRAR STREAMS Y SOCKETS
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }
}// ..
