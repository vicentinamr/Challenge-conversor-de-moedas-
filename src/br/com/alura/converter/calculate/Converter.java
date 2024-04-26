package br.com.alura.converter.calculate;

import java.util.Map;
public class Converter {
    private Map<String, Double> exchangeRates;

    public Converter(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 1.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 1.0);

        // Realiza o cálculo de conversão
        return amount * (toRate / fromRate);
    }
}
