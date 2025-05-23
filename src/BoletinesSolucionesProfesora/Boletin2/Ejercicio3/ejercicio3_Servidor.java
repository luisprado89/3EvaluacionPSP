package BoletinesSolucionesProfesora.Boletin2.Ejercicio3; // Paquete del ejercicio

import java.io.*; // Para flujos de entrada/salida
import java.net.*; // Para sockets

public class ejercicio3_Servidor {

    public static void main(String[] arg) throws IOException, ClassNotFoundException {

        int numeroPuerto = 6000; // Puerto donde el servidor escucha
        ServerSocket servidor = new ServerSocket(numeroPuerto); // Se abre el socket servidor

        System.out.println("Esperando al cliente.....");
        Socket cliente = servidor.accept(); // Espera y acepta una conexión del cliente

        // Flujo de salida para enviar objetos al cliente
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        // Flujo de entrada para recibir objetos del cliente
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());

        Numeros dato = new Numeros(); // Crea un objeto para almacenar el recibido

        try {
            dato = (Numeros) inObjeto.readObject(); // Recibe el primer objeto del cliente
        } catch (SocketException ce) {
            System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE...." + ce.getMessage());
            System.exit(0); // Finaliza si hay un error
        }

        // Mientras el número recibido sea mayor que 0
        while (dato.getNumero() > 0) {
            long cuadrado = dato.getNumero() * dato.getNumero(); // Calcula el cuadrado
            long cubo = cuadrado * dato.getNumero(); // Calcula el cubo

            dato.setCuadrado(cuadrado); // Asigna el cuadrado al objeto
            dato.setCubo(cubo); // Asigna el cubo al objeto

            System.out.println("Recibo: " + dato.getNumero()); // Muestra el número recibido

            outObjeto.writeObject(dato); // Envia el objeto actualizado al cliente

            // Espera el siguiente número del cliente
            try {
                dato = (Numeros) inObjeto.readObject();
            } catch (SocketException ce) {
                System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE...." + ce.getMessage());
                System.exit(0); // Si hay error, termina
            }
        }

        System.out.println("SERVIDOR FINALIZADO....");

        // Cierre de flujos y sockets
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }
}
