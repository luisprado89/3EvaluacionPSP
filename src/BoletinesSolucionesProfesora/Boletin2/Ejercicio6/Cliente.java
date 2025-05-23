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

        String host = "localhost";
        int puerto = 12345;// puerto remoto
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket cliente = null;
        try {
            cliente = new Socket(host, puerto);
        } catch (ConnectException s) {
            System.out.println("SERVIDOR NO CONECTADO...");
            System.exit(1);
        }

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        Scanner sc = new Scanner(System.in);
        String cadena;
        do {
            System.out.print("Introduce cadena: ");
            cadena = sc.nextLine();
            flujoSalida.writeUTF(cadena);

            if (!cadena.equals("*")) {
                System.out.println("RESPUESTA DEL SERVIDOR: " + flujoEntrada.readUTF());
            }
        } while (!cadena.equals("*"));

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();

        System.out.print("Fin de cliente... ");
    }// main
}

