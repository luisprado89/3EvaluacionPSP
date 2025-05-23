package BoletinesSolucionesProfesora.Boletin1.Ejercicio5; // Define el paquete del ejercicio

import java.io.DataOutputStream; // Permite enviar datos primitivos como UTF
import java.io.IOException; // Para manejar excepciones de entrada/salida
import java.io.OutputStream; // Clase base para salida de datos
import java.net.ServerSocket; // Clase para crear un socket servidor
import java.net.Socket; // Clase que representa la conexión con un cliente
import java.util.Scanner; // Para leer datos desde el teclado

public class ejercicio5b_Servidor { // Clase principal del servidor

    public static void main(String[] arg) throws IOException { // Método principal

        Scanner sc = new Scanner(System.in); // Scanner para leer el número de clientes desde consola
        int Puerto = 6000, N = 0; // Puerto donde escuchará el servidor y número de clientes a atender
        boolean numValido = false; // Bandera para validar la entrada numérica

        // INICIO EL SERVIDOR
        ServerSocket Servidor = new ServerSocket(Puerto); // Crea el servidor en el puerto 6000

        // PIDE EL NÚMERO DE CLIENTES
        while (!numValido) {
            System.out.println("Introduzca el número de clientes que se van a conectar.");
            try {
                N = sc.nextInt(); // Intenta leer un número entero
                if (N < 0) numValido = false; // Si es negativo, no es válido
                else numValido = true; // Valor correcto
            } catch (Exception e) {
                System.out.println("Formato numérico incorrecto"); // Si no se puede convertir, muestra error
                numValido = false; // Vuelve a pedir
                sc.nextLine(); // Limpia el buffer del scanner
            }
        }

        System.out.println("Escuchando en el puerto " + Puerto); // Muestra que el servidor está en espera

        // Acepta peticiones de N clientes
        for (int i = 1; i <= N; i++) {
            Socket Cliente = Servidor.accept(); // Espera conexión de un cliente
            System.out.println("Sirviendo al cliente " + i); // Informa del cliente que está siendo atendido

            // CREO FLUJO DE SALIDA AL CLIENTE
            OutputStream aux = Cliente.getOutputStream(); // Obtiene flujo de salida del socket
            DataOutputStream flujo = new DataOutputStream(aux); // Envuelve el flujo para enviar UTF

            // LE ENVÍO UN SALUDO
            flujo.writeUTF("Saludo al cliente " + i); // Envia el mensaje al cliente indicando su número

            Cliente.close(); // Cierra el socket con el cliente tras enviar el saludo
        }

        Servidor.close(); // Cierra el servidor después de atender a N clientes
        System.out.println("No hay más clientes..."); // Mensaje final
    } // Fin del main
} // Fin de la clase
