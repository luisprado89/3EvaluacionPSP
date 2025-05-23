package BoletinesSolucionesProfesora.Boletin1.Ejercicio5;
/*
 * Boletín 1 SOCKETS
 * Ejercicio 5
 * Crea un programa servidor que pueda atender hasta 3 clientes. Debe enviar a cada cliente un
 * mensaje indicando el número de cliente que es. Este número será 1, 2 o 3. El cliente mostrará
 * el mensaje recibido. Cambia el programa para que lo haga para N clientes, siendo N un
 * parámetro que tendrás que definir en el programa.
 */
import java.io.*;
import java.net.*;

public class ejercicio5_Cliente {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 6000;
        System.out.println("Conectando con el servidor...");

        // ABRIR SOCKET
        Socket Cliente = null;

        try {
            Cliente = new Socket(Host, Puerto);
        } catch (ConnectException c) {
            System.out.println("SERVIDOR CERRADO. ");
            return;
        }
        // RECIBO SALUDO DEL SERVIDOR
        InputStream aux = Cliente.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);

        System.out.println("Recibiendo del servidor: " + flujo.readUTF());

        System.out.println("CLIENTE CERRADO. ");

        Cliente.close();// Cierra el socket
    }// main
}//
