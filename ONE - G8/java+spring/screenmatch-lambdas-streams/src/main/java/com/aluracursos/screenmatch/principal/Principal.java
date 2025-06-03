package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPi;
import com.aluracursos.screenmatch.service.ConvierteDatos;


import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPi consumoApi = new ConsumoAPi();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=710cdc35";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");
        String nombreSerie = teclado.nextLine();
        //busca los datos generales de las series
        String json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        //busca los datos de todas las temporadas sin organizar
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporadas datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        //todas las temporadas con us episodios organizado en titulos y numero de temporada
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            System.out.println("Temporada: " + temporadas.get(i).numero());
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println( "Episodio: " + episodiosTemporada.get(j).titulo());
//            }
//        }
        //usando streams, dejando todo en una sola linea
        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Episodio: " + e.titulo())));
        //Convertir todad las informaciones en una lista del tipo dataos episodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
        //System.out.println(datosEpisodios);

        //Top 5 episodios de la serie
//        System.out.println("Top 5 episodios");
//        datosEpisodios.stream()
//                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro (N/A)" + e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("Segundo filtro ordenando (M>m)" + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Tercer filtro Mayusculas (m>M)" + e))
//                .limit(5)
//                .forEach(System.out::println);

        //Convirtiendo los datos a una lista del tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        //busqueda de episodio a partir de x año
//        System.out.println("Indica el año apartir del cual quieres ver los episodios: ");
//        var fecha = teclado.nextInt();
//
//        LocalDate fechaBuscada = LocalDate.of(fecha, 1, 1);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBuscada))
//                .forEach(e ->
//                                System.out.println(
//                                         "Temporada " + e.getTemporada() +
//                                         " Episodio " + e.getTitulo() +
//                                         " Fecha de lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
//                                ));
        //Busca episodios por pedazo de titulo
//        System.out.println("El titutlo del episodio que desea buscar ");
//        var pedazoTitulo = teclado.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
//                .findFirst();
//            if(episodioBuscado.isPresent()){
//                System.out.println("Episodio encontrado ");
//                System.out.println("Los datos son: " + episodioBuscado.get());
//            } else {
//                System.out.println("Episodio no encontrado");
//            }
        //Dandole una evaluacion a las temporadas
        Map<Integer, Double> evaluacionPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        //System.out.println(evaluacionPorTemporada);

        //Generando estadisticas
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Media de las evaluaciones " + est.getAverage());
        System.out.println("Episodio mejor evaluado " + est.getMax());
        System.out.println("Episodiod peor evaluado " + est.getMin());
    }
}

