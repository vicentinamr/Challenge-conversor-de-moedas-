package br.com.alura.converter.principal;
// → | Import dos Packages || Biblioteca GSON || IOException E/S || Scanner Input || Set
import br.com.alura.converter.models.ApiConectar;
import br.com.alura.converter.models.Conversor;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Aplicativo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            boolean continuar = true;
                // → |1. Escolhendo o primeiro tipo de moeda.|
            while (continuar) {
                System.out.println("______$$$$$____CONVERSOR___DE___MOEDAS____$$$$$______");
                System.out.println("");
                System.out.println("Digite a moeda que deseja converter (Ex: BRL, SBD) : ");
                String baseCurrency = scanner.nextLine();
                baseCurrency = baseCurrency.toUpperCase();

                // → |2. Chamando o método para obter as taxas de cambio.|
                // → |3. Obtendo o objeto taxa de conversão.|
                JsonObject ratesObject = ApiConectar.getExchangeRates(baseCurrency);
                JsonObject conversionRates = ratesObject.getAsJsonObject("conversion_rates");

                // → |4. Listando Moedas Disponíveis.|
                // → |5. Imprimir Moedas do conjunto currencies de forma organizada.|
                System.out.println("Moedas Disponíveis:");
                Set<String> currencies = conversionRates.keySet();
                int count = 0;
                for (String currency : currencies) {
                    System.out.printf("%-10s", currency);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
                System.out.println("\n");
                // → | 6. Input para qual tipo de moeda que sera convertida |
                System.out.println("Digite para qual moeda deseja converter (Exemplo: MXN):");
                String secondCurrency = scanner.nextLine();
                secondCurrency = secondCurrency.toUpperCase();
                System.out.println("Digite a quantia de " + baseCurrency + " que deseja converter para " + secondCurrency + ":");
                double amountToConvert = scanner.nextDouble();
                scanner.nextLine();
                // → | 7. Instancia da moeda convertida.|
                Conversor conversor = new Conversor(conversionRates); //

                String convertedAmount = conversor.convertirMoneda(baseCurrency, secondCurrency, amountToConvert);
                System.out.println(baseCurrency + " a " + secondCurrency + " = " + convertedAmount);

                System.out.println("Deseja realizar outra operação? (s/n)");
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuar = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao calcular taxa de cambio: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

