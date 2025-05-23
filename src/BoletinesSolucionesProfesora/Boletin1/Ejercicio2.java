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
        URL url = null;
        try {
            url = new URL("https://www.edu.xunta.gal");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in;
        try {
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//
}//Ejemplo2URL
