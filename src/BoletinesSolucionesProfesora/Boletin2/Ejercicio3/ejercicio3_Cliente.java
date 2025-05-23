package BoletinesSolucionesProfesora.Boletin2.Ejercicio3; // Paquete del ejercicio

import java.io.*; // Entrada/salida de objetos
import java.net.*; // Sockets
import java.util.InputMismatchException;
import java.util.Scanner;

public class ejercicio3_Cliente {

    public static void main(String[] arg) throws IOException, ClassNotFoundException {

        String host = "localhost"; // Dirección del servidor (local)
        int puerto = 6000; // Puerto en el que escucha el servidor

        Scanner sc = new Scanner(System.in); // Para leer desde teclado

        Socket cliente = null;
        try {
            cliente = new Socket(host, puerto); // Intenta conectar con el servidor
            System.out.println("PROGRAMA CLIENTE INICIADO....");
        } catch (ConnectException ce) { // Si no puede conectar
            System.out.println("ERROR AL ESTABLECER LA CONEXION CON EL SERVIDOR....");
            System.exit(0); // Termina el programa
        }

        // Flujo de entrada para leer objetos del servidor
        ObjectInputStream fEntrada = new ObjectInputStream(cliente.getInputStream());

        // Flujo de salida para enviar objetos al servidor
        ObjectOutputStream fSalida = new ObjectOutputStream(cliente.getOutputStream());

        int numero = 0;

        do {
            System.out.print("Introduce un numero: ");
            try {
                numero = sc.nextInt(); // Lee el número introducido
            } catch (InputMismatchException nn) {
                numero = 1; // Valor neutro para seguir
                System.out.print("\tNumero incorrecto...\n");
                sc.nextLine(); // Limpia entrada
                continue;
            }

            Numeros n = new Numeros(); // Crea un objeto Numeros
            n.setNumero(numero); // Establece el número introducido

            // Envía el objeto al servidor
            fSalida.writeObject(n);

            // Si el número es > 0, espera respuesta del servidor
            if (numero > 0) {
                Numeros dato = (Numeros) fEntrada.readObject(); // Lee el objeto recibido
                System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
            }

        } while (numero > 0); // Repetir mientras el número sea mayor que 0

        System.out.println("CLIENTE FINALIZADO....");

        // Cierra flujos y socket
        fEntrada.close();
        fSalida.close();
        cliente.close();
    }
}
