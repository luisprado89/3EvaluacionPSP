package BoletinesSolucionesProfesora.Boletin2.Ejercicio6;
import java.io.*;
import java.net.*;


public class Servidor {
	
	static ServerSocket servidor;
	static final int PUERTO = 12345;// puerto por el que escucho
		
	public static void main(String args[]) throws IOException {
		
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");
				
		while (true) {
			Socket s;
			try {
				s = servidor.accept();// esperando cliente
			} catch (SocketException ns) {
				break;
			}// sale cuando			
				
			HiloServidor hilo = new HiloServidor(s);
			hilo.start();
		}
		
		System.out.println("Servidor finalizado..."); 	
	}
}// ..

