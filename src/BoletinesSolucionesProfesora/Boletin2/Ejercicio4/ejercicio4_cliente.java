package BoletinesSolucionesProfesora.Boletin2.Ejercicio4;
/*
 * Boletin 2 SOCKETS
 * Ejercicio 4
 * 4. Usando sockets UDP, realiza un programa servidor que espere un datagrama de un cliente. El
 * cliente le envía un objeto Persona que previamente había inicializado. El servidor modifica los
 * datos del objeto Persona y se lo envía de vuelta al cliente. Visualiza los datos del objeto
 * Persona tanto en el programa cliente cuando los envía y los recibe como en el programa
 * servidor cuando los recibe y los envía modificados.
 */
import java.io.*;
import java.net.*;

public class ejercicio4_cliente {
	public static void main(String args[]) throws Exception {		
		// SOCKET cliente
		DatagramSocket clientSocket = new DatagramSocket();	
		byte[] recibidos = new byte[1024];

		// DATOS DEL SERVIDOR
		InetAddress IPServidor = InetAddress.getLocalHost();// localhost
		int puerto = 9876; // puerto por el que escucha

		Persona per = new Persona("Maria", 22);  		
		//convertimos objeto a bytes 
		ByteArrayOutputStream bs= new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream (bs);
	    os.writeObject(per);  // this es de tipo DatoUdp
	    os.close();
	    byte[] bytes =  bs.toByteArray(); // devuelve byte[]
		
	    // ENVIANDO AL SERVIDOR
		System.out.println("Enviando " + bytes.length + " bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPServidor, puerto);
		clientSocket.send(envio);

		// RECIBIENDO DEL SERVIDOR
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama....");
		clientSocket.receive(recibo);
		
		//CONVERTIMOS bytes a objeto		
		ByteArrayInputStream bais= new ByteArrayInputStream(recibidos); // bytes es el byte[]
	    ObjectInputStream is = new ObjectInputStream(bais);
	    Persona persona = (Persona)is.readObject();
	    is.close();

		//visualizo datos
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
		System.out.println("\tDatos: " + persona.getNombre() +"*"+persona.getEdad());
		clientSocket.close();//cerrar socket
	}
} 