package BoletinesSolucionesProfesora.Boletin2.Ejercicio1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

class HiloServidor extends Thread {
    DataInputStream fentrada;
    ObjectOutputStream fsalida;
    Socket socket = null;
    Profesor arrayObjetosProfesor[];
    Integer idcliente;

    public HiloServidor(Socket s, Profesor[] arrayObjetosProfesor, int idcliente) {
        socket = s;
        this.arrayObjetosProfesor = arrayObjetosProfesor;
        this.idcliente = idcliente;
        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S con cliente " + idcliente);
            e.printStackTrace();
        }
    }// ..

    public void run() {
        String cadena = "";
        try {
            fsalida.writeObject(idcliente);
        } catch (IOException e2) {
            System.out.println("ERROR AL ENVIAR IDCLIENTE CON CLIENTE " + idcliente);
            e2.printStackTrace();
        }

        while (!cadena.trim().equals("*")) {
            Profesor profesor;
            try {
                try {
                    cadena = fentrada.readUTF();
                } catch (SocketException dd) {
                    System.out.println("\tERROR AL LEER EL CLIENTE: " + idcliente);
                    break;
                } catch (EOFException EO) {
                    System.out.println("EL CLIENTE " + idcliente + " HA FINALIZADO ");
                    break;
                }
                System.out.println("\tConsultando id: " + cadena + ", solicitado por cliente: " + idcliente);

                int id = Integer.parseInt(cadena);
                profesor = DatosProfesor(id);


                fsalida.reset();
                fsalida.writeObject(profesor);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // fin while

        System.out.println("FIN CON: " + socket.toString() + " DEL CLIENTE:  " + idcliente);
        try {
            fsalida.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// run

    // devuelve el profesor de identificador
    private Profesor DatosProfesor(int identificador) {
        Especialidad noexiste = new Especialidad(0, "sin datos");

        Profesor profesor = new Profesor(identificador, "No existe", null, noexiste);

        for (int i = 0; i < arrayObjetosProfesor.length; i++) {
            if (arrayObjetosProfesor[i].getIdprofesor() == identificador)
                profesor = arrayObjetosProfesor[i];
        }
        return profesor;
    }

}//fin hilo