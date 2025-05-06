package lad.com.alura.conversormoneda;

import java.io.IOException;


import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Conversor.eleccionUserMenu();
        //Scanner scanner = new Scanner(System.in);
        //scanner.close();
        ObtenerTasa obtenerTasa = new ObtenerTasa();

        System.out.println(obtenerTasa.buscaTasa("USD"));
    }
}