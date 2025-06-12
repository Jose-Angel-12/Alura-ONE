package lad.com.alura.conversormoneda;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ObtenerTasa {
    public Tasa buscaTasa(String urlFinal) throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8c523e4abc74b04bb0737623/latest/" + urlFinal);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> respuesta = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Parseamos todo el JSON
        Tasa tasaCompleta = new Gson().fromJson(respuesta.body(), Tasa.class);

        // Códigos de monedas a conservar
        Set<String> monedasFiltradas = Set.of("ARS", "BRL", "COP", "USD");

        // Filtrar el mapa
        Map<String, Double> filtradas = tasaCompleta.conversion_rates().entrySet().stream()
                .filter(e -> monedasFiltradas.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Retornamos una nueva instancia `Tasa` con solo las monedas deseadas
        return new Tasa(tasaCompleta.base_code(), filtradas);
    }
}
