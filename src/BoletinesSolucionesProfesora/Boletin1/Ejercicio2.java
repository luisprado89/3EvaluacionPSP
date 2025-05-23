package BoletinesSolucionesProfesora.Boletin1;
/*
 * Boletín 1 SOCKETS
 * Ejercicio 2
 * 2. Implementa un programa que recoja de teclado una URL (con el formato
 * http://www.sitioweb.dom) y abra una conexión a dicho sitio Web, mostrando por pantalla el
 * código HTML de su página inicial.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio2 {
    public static void main(String[] args) {
        URL url = null; // Variable para almacenar el objeto URL

        try {
            url = new URL("https://www.edu.xunta.gal"); // Se crea un objeto URL con una dirección web
        } catch (MalformedURLException e) { // Captura si la URL está mal escrita
            e.printStackTrace(); // Imprime el error detallado
        }

        BufferedReader in; // Declaración del lector que se usará para leer el HTML de la página

        try {
            InputStream inputStream = url.openStream(); // Abre un flujo de entrada desde la URL
            in = new BufferedReader(new InputStreamReader(inputStream)); // Convierte el flujo de bytes a caracteres y lo envuelve en un buffer

            String inputLine; // Variable para almacenar cada línea leída

            // Mientras se puedan leer líneas de la página...
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine); // Se imprime cada línea del código HTML en consola

            in.close(); // Se cierra el lector para liberar recursos

        } catch (IOException e) { // Captura cualquier error al abrir o leer la URL
            e.printStackTrace(); // Imprime el error
        }
    } // Fin del método main
} // Fin de la clase Ejercicio2