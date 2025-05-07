package lad.com.alura.conversormoneda;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {

    private double convertirValor;
    private int opcion;
    private ObtenerTasa obtenerTasa;

    public double getConvertirValor() {
        return convertirValor;
    }

    public void setConvertirValor(double convertirValor) {
        this.convertirValor = convertirValor;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }


    public void exibirMenu() {
        System.out.println("*******************************************************\n" +
                "Sea bienvenido/a al Conversor de Moneda =]\n" +
                "*******************************************************\n" +
                "1) Dólar =>> Peso Argentino\n" +
                "2) Peso Argentino =>> Dólar\n" +
                "3) Dólar =>> Real Brasileño\n" +
                "4) Real Brasileño =>> Dólar\n" +
                "5) Dólar =>> Peso Colombiano\n" +
                "6) Peso Colombiano =>> Dólar\n" +
                "7) Salir\n" +
                "\n" +
                "Elija una opción válida:\n" +
                "*******************************************************\n");
    }

    public void eleccionUserMenu() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean close = true;

        while (close) {

            try {
                exibirMenu();
                setOpcion(scanner.nextInt());
                if (getOpcion() == 7) {
                    close = false;
                    break;
                }
                System.out.println("Ingrese el valor que desea convertir:");
                setConvertirValor(scanner.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor, introduzca un número.");
                scanner.nextLine();
                continue;
            }
            String monedaBase, monedaDeseada;

            switch (getOpcion()) {
                case 1:
                    monedaBase = "USD";
                    monedaDeseada = "ARS";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 2:
                    monedaBase = "ARS";
                    monedaDeseada = "USD";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 3:
                    monedaBase = "USD";
                    monedaDeseada = "BRL";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 4:
                    monedaBase = "BRL";
                    monedaDeseada = "USD";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 5:
                    monedaBase = "USD";
                    monedaDeseada = "COP";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 6:
                    monedaBase = "COP";
                    monedaDeseada = "USD";
                    resultado(monedaBase, monedaDeseada);
                    break;
                case 7:
                    close = false;
                    break;
            }
        }
        scanner.close();
    }

    public void resultado(String monedaBase, String monedaDeseada) throws IOException, InterruptedException {
        this.obtenerTasa = new ObtenerTasa();
        System.out.println("El valor de " + getConvertirValor() + " [" + monedaBase + "]" + " Corresponde al valor final de =>>> " + this.obtenerTasa.buscaTasa(monedaBase).conversion_rates().get(monedaDeseada) * getConvertirValor() + " [" + monedaDeseada + "]\n");
    }
}


