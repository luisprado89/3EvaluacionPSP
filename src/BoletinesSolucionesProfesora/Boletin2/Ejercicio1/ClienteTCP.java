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
import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class ClienteTCP {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int puerto = 6000;// puerto remoto
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
        ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());

        Integer idcliente = (Integer) flujoEntrada.readObject();
        System.out.println("SOY EL CLIENTE: " + idcliente);

        String cadena = "";
        Profesor profe;
        while (true) {
            System.out.println("=====================================================");
            System.out.print("Introduce identificador a consultar: ");
            cadena = in.readLine();

            if (cadena.trim().equals("*"))
                break;

            try {
                Integer.parseInt(cadena);
            } catch (NumberFormatException nex) {
                System.out.println("\tIdentificador incorrecto: ");
                continue;
            }

            // ENVIANDO AL SERVIDOR
            try {
                flujoSalida.writeUTF(cadena);
            } catch (SocketException se) {
                System.out.println("ERROR AL ENVIAR DATOS AL SERVIDOR (el proceo finalizar�)...");
                break;
            }

            // RECIBIENDO DEL SERVIDOR
            profe = (Profesor) flujoEntrada.readObject();

            // visualizo datos
            System.out.printf("\tNombre: %s, Especialidad: %d - %s %n", profe.getNombre(), profe.getEspe().getId(),
                    profe.getEspe().getNombreespe());

            Asignatura[] asig = profe.getAsignaturas();
            try {
                for (int j = 0; j < asig.length; j++) {
                    System.out.printf("\t\tAsignatura: %d - %s %n", asig[j].getId(), asig[j].getNombreasig());
                }
            } catch (NullPointerException m) {
            }

        } // WHILE TRUE

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();

        System.out.print("Fin de cliente... ");
    }// main

}
