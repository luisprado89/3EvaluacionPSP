package BoletinesSolucionesProfesora.Boletin2.Ejercicio3;
/*
* Boletin 2 SOCKETS
* Ejercicio 3
* 3. Crea una clase Java llamada Numeros que defina 3 atributos , uno de ellos entero, y los otros
* 2 de tipo long:
* int numero;
* long cuadrado;
* long cubo;
*  Define un constructor con parámetros y otro sin parámetros. Define los métodos get y set de
* los atributos. Crea un programa cliente que introduzca por teclado un número e inicialice un
* objeto Numeros, el atributo numero debe contener el número introducido por teclado. Debe
* enviar ese objeto al programa servidor. El proceso se repetirá mientras el número introducido
* por teclado sea mayor que 0.
*
* Crea un programa servidor que reciba un objeto Numeros. Debe calcular el cuadrado y el cubo
* del atributo numero y debe enviar el objeto al cliente con los cálculos realizados, el cuadrado
* y el cubo en sus atributos respectivos. El cliente recibirá el objeto y visualizará el cuadrado y
* el cubo del número introducido por teclado. El programa servidor finalizará cuando el número
* recibido en el objeto Numeros sea menor o igual que 0.
*
* Controlar posibles errores, por ejemplo si ejecutamos el cliente y el servidor no está iniciado,
* o si estando el servidor ejecutándose ocurre algún error en el cliente, o este finaliza
* inesperadamente, etc.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente_UDP {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		int Puerto = 6000;// puerto remoto
		InetAddress IPServidor = InetAddress.getLocalHost();// localhost
		Scanner sc = new Scanner(System.in);		
		
		DatagramSocket cliente = null;
		try {
			cliente = new DatagramSocket();
			System.out.println("PROGRAMA CLIENTE INICIADO....");
		} catch (ConnectException ce) {
			System.out.println("ERROR AL ESTABLECER LA CONEXI�N CON EL SERVIDOR....");
			System.exit(0);
		}
		
		
    	int numero = 0;

		do {			
			System.out.print("Introduce un numero: ");

			try {
				numero = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException nn) {
				sc.nextLine();
				numero = 1;
				System.out.print("\tN�mero incorrecto...\n");
				continue;
			}
			
			Numeros n = new Numeros();
			n.setNumero(numero);

			//CONVERTIMOS OBJETO A BYTES
			ByteArrayOutputStream bs= new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream (bs);
			
			out.reset();
			out.writeObject(n); //escribir objeto 
			byte[] enviados =  bs.toByteArray(); // objeto en bytes
			
			//envir objeto
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, Puerto);
			cliente.send(envio);
			
			// Se recibe un objeto
			if (numero > 0) {				
				byte[] recibidos = new byte[1024];
				DatagramPacket paqRecibido = new 
				               DatagramPacket(recibidos, recibidos.length);
				cliente.receive(paqRecibido); //recibo el datagrama

				// CONVERTIMOS BYTES A OBJETO
				ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); 
				ObjectInputStream in = new ObjectInputStream(bais);
				
				Numeros dato  = new Numeros();
				dato = (Numeros) in.readObject();//obtengo objeto
                in.close();
                
				System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
			}

		} while (numero > 0);

		System.out.println("CLIENTE UDP FINALIZADO....");

		// CERRAR STREAMS Y SOCKETS
		
		cliente.close();

	}

}
