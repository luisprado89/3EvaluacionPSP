package BoletinesSolucionesProfesora.Boletin2.Ejercicio4;

import java.io.*;
import java.net.*;

public class ejercicio4_servidor {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);

        System.out.println("Esperando datagrama.....");
        // RECIBO DATAGRAMA
        byte[] recibidos = new byte[1024];
        DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
        serverSocket.receive(paqRecibido);

        // CONVERTIMOS bytes a objeto
        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
        ObjectInputStream in = new ObjectInputStream(bais);
        Persona persona = (Persona) in.readObject();
        in.close();

        // DIRECCION ORIGEN
        InetAddress IPOrigen = paqRecibido.getAddress();
        int puerto = paqRecibido.getPort();
        System.out.println("\tProcedente de: " + IPOrigen + ":" + puerto);
        System.out.println("\tDatos: " + persona.getNombre() + "*" + persona.getEdad());

        //cambiamos los datos
        persona.setNombre("Maria Jesus");
        persona.setEdad(34);

        //convertimos objeto a bytes
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(persona);  // es de tipo DatoUdp
        os.close();
        byte[] bytes = bs.toByteArray(); // devuelve byte[]

        // ENVIANDO AL CLIENTE
        System.out.println("Enviando " + bytes.length + " bytes al cliente.");
        DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPOrigen, puerto);
        serverSocket.send(envio);
        serverSocket.close();
        System.out.println("Socket cerrado...");
    }
}
