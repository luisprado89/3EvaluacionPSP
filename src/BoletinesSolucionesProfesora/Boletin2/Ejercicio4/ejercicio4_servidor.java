package BoletinesSolucionesProfesora.Boletin2.Ejercicio4; // Paquete del ejercicio

import java.io.*;
import java.net.*;

public class ejercicio4_servidor {

    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876); // Crea el socket UDP en el puerto 9876

        System.out.println("Esperando datagrama.....");

        // 1. RECIBIR DATAGRAMA
        byte[] recibidos = new byte[1024]; // Buffer para los datos entrantes
        DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
        serverSocket.receive(paqRecibido); // Espera y recibe el datagrama UDP

        // 2. CONVERTIR BYTES A OBJETO Persona
        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); // Flujo de entrada desde byte[]
        ObjectInputStream in = new ObjectInputStream(bais); // Flujo para leer objetos
        Persona persona = (Persona) in.readObject(); // Reconstruye el objeto Persona
        in.close(); // Cierra el flujo

        // 3. MUESTRA DATOS DEL OBJETO RECIBIDO
        InetAddress IPOrigen = paqRecibido.getAddress(); // Dirección del cliente
        int puerto = paqRecibido.getPort(); // Puerto del cliente
        System.out.println("\tProcedente de: " + IPOrigen + ":" + puerto);
        System.out.println("\tDatos: " + persona.getNombre() + "*" + persona.getEdad());

        // 4. MODIFICAR DATOS DEL OBJETO
        persona.setNombre("Maria Jesus"); // Cambia el nombre
        persona.setEdad(34); // Cambia la edad

        // 5. CONVERTIR OBJETO MODIFICADO A BYTES
        ByteArrayOutputStream bs = new ByteArrayOutputStream(); // Flujo de salida en memoria
        ObjectOutputStream os = new ObjectOutputStream(bs); // Flujo para escribir objetos
        os.writeObject(persona); // Serializa el objeto Persona modificado
        os.close();
        byte[] bytes = bs.toByteArray(); // Obtiene el byte[] del objeto

        // 6. ENVIAR OBJETO MODIFICADO AL CLIENTE
        System.out.println("Enviando " + bytes.length + " bytes al cliente.");
        DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPOrigen, puerto);
        serverSocket.send(envio); // Envía el datagrama al cliente

        // 7. CERRAR SOCKET
        serverSocket.close();
        System.out.println("Socket cerrado...");
    }
}
