package br.com.alura.converter.models;
// → | Import para converter java para JSON |
import com.google.gson.JsonObject;

public class Conversor {
    private JsonObject taxasDeCambio;

    public Conversor(JsonObject taxasDeCambio) {
        this.taxasDeCambio = taxasDeCambio;
    }
    public String convertirMoneda(String moedaOrigem, String moedaDestino, double quantidade) {
        moedaOrigem = moedaOrigem.toUpperCase();
        moedaDestino = moedaDestino.toUpperCase();

        if (taxasDeCambio.has(moedaOrigem) && taxasDeCambio.has(moedaDestino)) {
            double origemDestino = taxasDeCambio.get(moedaDestino).getAsDouble();
            double destinoOrigem = taxasDeCambio.get(moedaOrigem).getAsDouble();
            double quantidadeConvertida = (quantidade * origemDestino) / destinoOrigem;
            return String.format("%.2f", quantidadeConvertida);
        } else {
            return "Moeda não encontrada.";
        }
    }
}

