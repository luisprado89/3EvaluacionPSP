package BoletinesSolucionesProfesora.Boletin2.Ejercicio5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servidorUDP {

	static Alumno arrayObjetos[]=new Alumno[5];
	
	public static void main(String args[]) throws Exception {
		
		//Creamos objetos en cada posicion
		
		Curso dam1 = new Curso("1","Primero de CFGS DAM");
		Curso dam2 = new Curso("2","Segundo de DAM");		
		
		arrayObjetos[0]=new Alumno("20","Fernando", dam1, 6);
		arrayObjetos[1]=new Alumno("32","Epi", dam2, 4);
		arrayObjetos[2]=new Alumno("1", "Blas", dam2, 8);
		arrayObjetos[3]=new Alumno("25","Manuela", dam1, 6);
		arrayObjetos[4]=new Alumno("4","Alicia", dam2, 4);
		
		DatagramSocket serverSocket = new DatagramSocket(9876);

		while (true) {
			System.out.println("Servidor Esperando identificador.....");
			// RECIBO DATAGRAMA
			byte[] recibidos = new byte[1024];
			byte[] enviados = new byte[1024];

			// RECIBO DATAGRAMA	
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido); //recibo el datagrama

			// CONVERTIMOS BYTES A OBJETO INTEGER
			ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); 
			ObjectInputStream in = new ObjectInputStream(bais);
			String identificador = (String) in.readObject();//obtengo objeto
			in.close();

			// DIRECCION ORIGEN
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			
			System.out.println("\tConsultando id: " + identificador);
			Alumno alum = DatosAlumno(identificador);
			
			//CONVERTIMOS OBJETO A BYTES  		
			ByteArrayOutputStream bs= new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream (bs);
			out.writeObject(alum); //escribir objeto en el stream
			out.close();  //cerrar stream
			enviados =  bs.toByteArray(); // objeto en bytes

			// ENVIO DATAGRAMA
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);

		}
		//serverSocket.close();
		//System.out.println("Socket cerrado...");
	}

	//devuelve el alumno de identificador i
	private static Alumno DatosAlumno(String identificador) {
		Curso noexiste = new Curso("*","Sin datos");
		Alumno al = new Alumno(identificador, "No existe", noexiste, -1);
		for (int i=0;i<arrayObjetos.length;i++){
			if (arrayObjetos[i].getIdalumno().equals(identificador))
			   al = arrayObjetos[i];
		}		
		return al;
	}
}
