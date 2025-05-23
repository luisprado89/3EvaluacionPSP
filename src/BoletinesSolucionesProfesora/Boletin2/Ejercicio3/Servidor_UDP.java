package BoletinesSolucionesProfesora.Boletin2.Ejercicio3; // Paquete del ejercicio

import java.io.*;
import java.net.*;

public class Servidor_UDP {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		DatagramSocket serverSocket = new DatagramSocket(6000); // Crea el socket UDP en el puerto 6000

		Numeros dato = new Numeros(); // Crea un objeto Numeros vacío
		dato.setNumero(1); // Inicializa con un valor mayor que 0 para entrar en el bucle

		System.out.println("SERVIDOR UDP INICIADO");

		while (dato.getNumero() > 0) { // Bucle mientras se reciban números mayores a 0

			dato = new Numeros(); // Crea un nuevo objeto para el nuevo ciclo

			// 1. RECIBO EL DATAGRAMA
			byte[] recibidos = new byte[1024]; // Buffer para recibir los datos
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido); // Espera a recibir un datagrama

			// 2. CONVIERTO LOS BYTES A OBJETO
			ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); // Flujo de entrada desde los bytes
			ObjectInputStream in = new ObjectInputStream(bais); // Flujo para leer el objeto
			dato = (Numeros) in.readObject(); // Reconstruye el objeto `Numeros`

			// 3. CALCULO CUADRADO Y CUBO
			long cuadrado = dato.getNumero() * dato.getNumero();
			long cubo = cuadrado * dato.getNumero();
			dato.setCuadrado(cuadrado); // Asigna el cuadrado
			dato.setCubo(cubo); // Asigna el cubo

			System.out.println("Recibo: " + dato.getNumero()); // Muestra el número recibido

			// 4. OBTENGO LA DIRECCIÓN Y PUERTO DEL CLIENTE
			InetAddress IPOrigen = paqRecibido.getAddress(); // Dirección IP del remitente
			int puerto = paqRecibido.getPort(); // Puerto desde donde envió el cliente

			// 5. CONVIERTO EL OBJETO A BYTES PARA RESPONDER
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bs);
			out.reset(); // Elimina referencias previas para evitar problemas de serialización
			out.writeObject(dato); // Serializa el objeto
			byte[] bytes = bs.toByteArray(); // Lo convierte en array de bytes

			// 6. ENVÍO EL DATAGRAMA DE RESPUESTA
			DatagramPacket paqEnviado = new DatagramPacket(bytes, bytes.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado); // Envía el datagrama al cliente

		}

		System.out.println("SERVIDOR UDP FINALIZADO....");

		// CIERRO EL SOCKET
		serverSocket.close();
	}
}
