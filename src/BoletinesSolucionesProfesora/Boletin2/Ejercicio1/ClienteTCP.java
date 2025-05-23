package BoletinesSolucionesProfesora.Boletin2.Ejercicio1;
/*
* Boletin 2 SOCKETS
* Ejercicio 1
* 1. Crea las siguientes clases con los siguientes atributos, los constructores y los métodos get y
* set necesarios:
* Clase Asignatura:
* int id;
* String nombreAsig;
*
* Clase Especialidad:
* int id;
* String nombreEsp;
*
* Clase Profesor:
* int idProfesor;
* String nombre;
* Asignatura [] asignaturas;
* Especialidad esp;
*
* Un profesor podrá impartir hasta 3 asignaturas.
* Utilizando sockets TCP, implementar un programa servidor que inicialice un array de 5 objetos
* de tipo Profesor. El servidor aceptará conexiones de clientes en un bucle infinito. Cada vez
* que se conecte un cliente, el servidor le asignará un id, que empezará en 1 e irá
* incrementándose cada vez que se conecta un nuevo cliente. El servidor recibirá del cliente un
* idProfesor y le devolverá el objeto Profesor asociado.
*
* Crea un programa cliente en el que se introduzca por teclado el idProfesor que se desea
* consultar. Dicho programa recogerá datos hasta recibir un * por parte del usuario.
* Si el idProfesor no se encuentra registrado, el servidor le devolverá un objeto Profesor con
* datos vacíos
 */

import java.io.BufferedReader; // Para leer desde teclado
import java.io.DataOutputStream; // Para enviar datos primitivos (UTF) al servidor
import java.io.InputStreamReader; // Para leer texto desde la entrada estándar
import java.io.ObjectInputStream; // Para recibir objetos serializados
import java.net.ConnectException; // Para capturar fallos al conectar con el servidor
import java.net.Socket; // Clase para conexión TCP cliente
import java.net.SocketException; // Para errores en la comunicación del socket

public class ClienteTCP {

    public static void main(String[] args) throws Exception {

        String host = "localhost"; // Dirección del servidor
        int puerto = 6000; // Puerto del servidor

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Lee desde teclado

        System.out.println("PROGRAMA CLIENTE INICIADO....");

        Socket cliente = null;
        try {
            cliente = new Socket(host, puerto); // Intenta conectar con el servidor
        } catch (ConnectException s) {
            System.out.println("SERVIDOR NO CONECTADO..."); // Error si el servidor no está disponible
            System.exit(1);
        }

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        // CREO FLUJO DE ENTRADA DE OBJETOS DESDE EL SERVIDOR
        ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());

        // Recibe el identificador que le asigna el servidor
        Integer idcliente = (Integer) flujoEntrada.readObject();
        System.out.println("SOY EL CLIENTE: " + idcliente); // Muestra su ID

        String cadena = ""; // Para almacenar entrada del usuario
        Profesor profe; // Objeto Profesor recibido del servidor

        while (true) {
            System.out.println("=====================================================");
            System.out.print("Introduce identificador a consultar: ");
            cadena = in.readLine(); // Lee el ID introducido

            if (cadena.trim().equals("*")) // Si se introduce *, finaliza
                break;

            try {
                Integer.parseInt(cadena); // Verifica que la entrada es un número
            } catch (NumberFormatException nex) {
                System.out.println("\tIdentificador incorrecto: ");
                continue; // Vuelve a pedir si no es válido
            }

            // ENVIANDO AL SERVIDOR
            try {
                flujoSalida.writeUTF(cadena); // Envía el ID al servidor
            } catch (SocketException se) {
                System.out.println("ERROR AL ENVIAR DATOS AL SERVIDOR (el proceso finalizará)...");
                break; // Termina si hay un error de conexión
            }

            // RECIBIENDO OBJETO PROFESOR DEL SERVIDOR
            profe = (Profesor) flujoEntrada.readObject();

            // Muestra los datos recibidos del profesor
            System.out.printf("\tNombre: %s, Especialidad: %d - %s %n",
                    profe.getNombre(), // Nombre del profesor
                    profe.getEspe().getId(), // ID de la especialidad
                    profe.getEspe().getNombreespe()); // Nombre de la especialidad

            Asignatura[] asig = profe.getAsignaturas(); // Obtiene el array de asignaturas

            // Imprime cada asignatura asociada (si las hay)
            try {
                for (int j = 0; j < asig.length; j++) {
                    System.out.printf("\t\tAsignatura: %d - %s %n",
                            asig[j].getId(), asig[j].getNombreasig());
                }
            } catch (NullPointerException m) {
                // Ignora si hay nulls en el array
            }

        } // FIN DEL BUCLE PRINCIPAL

        // CIERRE DE FLUJOS Y CONEXIÓN
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();

        System.out.print("Fin de cliente... ");
    }
}