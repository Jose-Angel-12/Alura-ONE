package lad.com.alura.conversormoneda;

import java.io.IOException;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Conversor conversor = new Conversor();
        conversor.eleccionUserMenu();

    }
}