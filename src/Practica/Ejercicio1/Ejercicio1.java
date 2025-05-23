package Practica.Ejercicio1;

import java.net.InetAddress;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String maquina = "";
        InetAddress inetAddress = null;
        while (!maquina.equals("*")){
            System.out.println("Introuzca una IP: ");
            maquina = scanner.nextLine();

        }
    }
}
