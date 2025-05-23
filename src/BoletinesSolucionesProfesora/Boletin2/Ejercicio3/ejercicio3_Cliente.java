package BoletinesSolucionesProfesora.Boletin2.Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ejercicio3_Cliente {
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int puerto = 6000;// puerto remoto

        Scanner sc = new Scanner(System.in);

        Socket cliente = null;
        try {
            cliente = new Socket(host, puerto);
            System.out.println("PROGRAMA CLIENTE INICIADO....");
        } catch (ConnectException ce) {
            System.out.println("ERROR AL ESTABLECER LA CONEXION CON EL SERVIDOR....");
            System.exit(0);
        }
        // Flujo de entrada para objetos
        ObjectInputStream fEntrada = new ObjectInputStream(cliente.getInputStream());

        // FLUJO DE salida para objetos
        ObjectOutputStream fSalida = new ObjectOutputStream(cliente.getOutputStream());

        int numero = 0;

        do {
            System.out.print("Introduce un numero: ");

            try {
                numero = sc.nextInt();
                //sc.nextLine();
            } catch (InputMismatchException nn) {
                //sc.nextLine();
                numero = 1;
                System.out.print("\tNumero incorrecto...\n");
                continue;
            }
            Numeros n = new Numeros();
            n.setNumero(numero);
            //Numeros n = new Numeros(numero);

            // Se envia el objeto
            fSalida.writeObject(n);

            // Se recibe un objeto
            if (numero > 0) {
                Numeros dato = (Numeros) fEntrada.readObject();// recibo objeto

                System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
            }

        } while (numero > 0);

        System.out.println("CLIENTE FINALIZADO....");

        // CERRAR STREAMS Y SOCKETS
        fEntrada.close();
        fSalida.close();
        cliente.close();
    }
}// ..
