package lad.com.alura.conversormoneda;

import java.util.Map;

public record Tasa(String base_code,
                   Map<String, Double> conversion_rates) {
}
