package com.gelse.casadellibro.principal;

import com.gelse.casadellibro.model.Datos;
import com.gelse.casadellibro.model.DatosLibros;
import com.gelse.casadellibro.service.ConsumoAPI;
import com.gelse.casadellibro.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        //imprimir 5 datos de libros
        System.out.println("Datos");
        datos.resultados().stream().limit(5).forEach(System.out::println);

        //Top 5 mejores libros
        System.out.println("\n10 mejores libros");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        //Busqueda de libros por nombre
        System.out.println("Introduzca el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println("Titulo: " + libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }

        //Estadisticas de todos los datos segun las descargas
        DoubleSummaryStatistics est = datos.resultados().stream()
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Media de descargas: " + est.getAverage());
        System.out.println("Numero mayor de descargas: " + est.getMax());
        System.out.println("Numero menor de descargas: " + est.getMin());
        System.out.println("Numero de registros contados: " + est.getCount());

    }
}
