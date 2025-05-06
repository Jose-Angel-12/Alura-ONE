package lad.com.alura.conversormoneda;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ObtenerTasa {
    public Tasa buscaTasa(String urlFinal) throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8c523e4abc74b04bb0737623/latest/" + urlFinal);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> respuesta = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(respuesta.body(), Tasa.class);
    }
}
