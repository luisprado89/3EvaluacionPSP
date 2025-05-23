package BoletinesSolucionesProfesora.Boletin1.Ejercicio5;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ejercicio5b_Servidor {
    public static void main(String[] arg) throws IOException {
        Scanner sc = new Scanner(System.in);
        int Puerto = 6000, N = 0;
        boolean numValido = false;
        // INICIO EL SERVIDOR
        ServerSocket Servidor = new ServerSocket(Puerto);
        while (!numValido) {
            System.out.println("Introduzca el número de clientes que se van a conectar.");
            try {
                N = sc.nextInt();
                if (N < 0) numValido = false;
                else numValido = true;
            } catch (Exception e) {
                System.out.println("Formato numerico incorrecto");
                numValido = false;
            }
        }
        System.out.println("Escuchando en el puerto " + Puerto);

        //Acepto peticiones de 3 clientes
        for (int i = 1; i <= N; i++) {
            Socket Cliente = Servidor.accept(); // ACEPTA PETICION
            System.out.println("Sirviendo al cliente " + i);

            // CREO FLUJO DE SALIDA AL CLIENTE
            OutputStream aux = Cliente.getOutputStream();
            DataOutputStream flujo = new DataOutputStream(aux);

            // LE ENVIO UN SALUDO
            flujo.writeUTF("Saludo al cliente " + i);
            Cliente.close(); //cierro el socket cliente
        }
        Servidor.close();    //cierro socket servidor
        System.out.println("No hay mas clientes...");
    }// main
}// fin
