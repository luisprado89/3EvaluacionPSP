package BoletinesSolucionesProfesora.Boletin2.Ejercicio2;
/*
* Boletin 2 SOCKETS
* Ejercicio 2
* 2. Crea un programa cliente usando sockets UDP que envíe el texto escrito desde la entrada
* estándar al servidor. El servidor le devolverá la cadena en mayúsculas. El proceso de entrada
* de datos finalizará cuando el cliente introduzca un asterisco.
* Crea un programa servidor que reciba cadenas de caracteres, las muestre en pantalla y se las
* envíe al emisor en mayúscula. El proceso servidor finalizará cuando reciba un asterisco.
*
* Establece un tiempo de espera de 5000ms con el método setSoTimeout para hacer que el
* método receive() del programa cliente se bloquee. Pasado ese tiempo, controlar si no se
* reciben datos lanzando la excepción InterruptedIOException, en cuyo caso visualiza un
* mensaje indicando que el paquete se ha perdido.
 */

import java.io.IOException;
import java.io.InterruptedIOException; // Excepción lanzada al pasar el tiempo de espera
import java.net.DatagramPacket; // Para enviar/recibir datos
import java.net.DatagramSocket; // Socket UDP
import java.net.InetAddress; // Dirección IP
import java.util.Scanner; // Entrada desde teclado

public class ejercicio2_Cliente {

    private static Scanner sc;

    public static void main(String[] args) throws IOException {

        InetAddress destino = InetAddress.getLocalHost(); // IP destino (localhost)
        int port = 12345; // Puerto al que se enviarán los datagramas

        byte[] mensaje = new byte[1024]; // Buffer para el mensaje a enviar

        DatagramPacket envio; // Objeto para representar el paquete a enviar
        DatagramPacket recibo; // Objeto para representar el paquete recibido
        DatagramSocket socket = new DatagramSocket(); // Crea el socket UDP

        String cadena;
        socket.setSoTimeout(5000); // Establece un tiempo máximo de espera de 5 segundos

        sc = new Scanner(System.in); // Scanner para leer desde teclado

        System.out.print("Introduce cadena: ");
        cadena = sc.nextLine(); // Lee la primera cadena

        while (!cadena.equals("*")) { // Mientras no se introduzca '*'
            mensaje = new byte[1024]; // Reinicia el buffer
            mensaje = cadena.getBytes(); // Convierte la cadena a bytes
            envio = new DatagramPacket(mensaje, mensaje.length, destino, port); // Prepara el paquete a enviar
            socket.send(envio); // Envia el datagrama al servidor

            // Recibo la respuesta del servidor
            byte[] buferrec = new byte[1024]; // Buffer para recibir respuesta
            recibo = new DatagramPacket(buferrec, buferrec.length); // Paquete donde se almacenará la respuesta

            try {
                socket.receive(recibo); // Espera la respuesta (hasta 5 segundos)
                String mayuscula = new String(recibo.getData()).trim(); // Convierte bytes a String y elimina espacios extra
                System.out.print("\tMayúsculas: " + mayuscula); // Muestra la respuesta
            } catch (InterruptedIOException i) { // Si pasa el tiempo de espera
                System.out.println("\tPAQUETE PERDIDO, SIN MAYÚSCULAS."); // Muestra mensaje de pérdida
            }

            System.out.print("\nIntroduce cadena: ");
            cadena = sc.nextLine(); // Lee la siguiente cadena
        }

        // Si el usuario introdujo '*', se lo envía al servidor para que finalice también
        envio = new DatagramPacket("*".getBytes(), "*".getBytes().length, destino, port);
        socket.send(envio); // Envía el mensaje de finalización

        socket.close(); // Cierra el socket UDP
        System.out.println("Fin de cliente ");
        sc.close(); // Cierra el Scanner
    }
}