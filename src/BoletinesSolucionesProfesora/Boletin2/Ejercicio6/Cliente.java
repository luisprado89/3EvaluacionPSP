package BoletinesSolucionesProfesora.Boletin2.Ejercicio6;
/*
 * Boletin 2 SOCKETS
 * Ejercicio 6
 * 6. Realiza un programa servidor que escuche en el puerto 12345. Cada vez que se conecte un
 * cliente se creará un nuevo hilo para atenderlo. Se mostrará en la consola del servidor la
 * dirección IP y el puerto remoto del cliente que se conecta. Se deberá notificar también cuando
 * un cliente se desconecte.
 * En el hilo que atiende al cliente, se reciben cadenas de caracteres que, mientras sean distintas
 * de “*”, se devolverán al cliente transformadas a mayúsculas.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {

        String host = "localhost"; // Dirección del servidor
        int puerto = 12345;        // Puerto donde escucha el servidor

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Entrada estándar

        System.out.println("PROGRAMA CLIENTE INICIADO....");

        Socket cliente = null;

        try {
            cliente = new Socket(host, puerto); // Conecta con el servidor
        } catch (ConnectException s) {
            System.out.println("SERVIDOR NO CONECTADO...");
            System.exit(1); // Termina si no puede conectarse
        }

        // Flujo de salida para enviar cadenas al servidor
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        // Flujo de entrada para recibir cadenas desde el servidor
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        Scanner sc = new Scanner(System.in); // Para leer desde teclado
        String cadena;

        do {
            System.out.print("Introduce cadena: ");
            cadena = sc.nextLine(); // Lee la cadena del usuario
            flujoSalida.writeUTF(cadena); // La envía al servidor

            if (!cadena.equals("*")) {
                // Si no es el fin, espera y muestra la respuesta del servidor
                System.out.println("RESPUESTA DEL SERVIDOR: " + flujoEntrada.readUTF());
            }

        } while (!cadena.equals("*")); // Repetir hasta que se introduzca '*'

        // Cierra recursos
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();

        System.out.print("Fin de cliente... ");
    }
}