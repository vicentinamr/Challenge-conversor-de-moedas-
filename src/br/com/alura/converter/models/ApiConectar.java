package br.com.alura.converter.models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class ApiConectar {
    // → Variáveis Para Capturar Os Valores Do Endereço da URL e Chave API
    // → Definida Como Privada e Como Se Fosse Uma Constante
    private static final String API_KEY = "40bdeba90e39d275eb1c0b0d";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/40bdeba90e39d275eb1c0b0d/latest/USD";

    public static JsonObject getExchangeRates(String baseCurrency) throws IOException {
        String apiUrl = API_BASE_URL + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBody = new StringBuilder();
            while (scanner.hasNextLine()) {
                responseBody.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            JsonParser parser = new JsonParser();
            return parser.parse(responseBody.toString()).getAsJsonObject();
        } else {
            throw new IOException("Falha ao biscar as taxas de câmbio. Código de resposta: " + conn.getResponseCode());
        }
    }
}


