package br.com.task;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Exemplo3 {

    /* Dado um vetor que guarda o valor de faturamento diário de uma distribuidora, faça um
    programa, na linguagem que desejar, que calcule e retorne:
    • O menor valor de faturamento ocorrido em um dia do mês;
    • O maior valor de faturamento ocorrido em um dia do mês;
    • Número de dias no mês em que o valor de faturamento diário foi superior à média mensal.

    IMPORTANTE:
    a) Usar o json ou xml disponível como fonte dos dados do faturamento mensal;
    b) Podem existir dias sem faturamento, como nos finais de semana e feriados.
    Estes dias devem ser ignorados no cálculo da média;
    * */
    public static void main(String[] args) {
        String filePath = "./faturamento.json";
        try {
            tryToReadFaturamento(filePath);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }
    }

    private static void tryToReadFaturamento(String filePath) throws IOException {
        var jsonArray = readJsonFile(filePath);
        var faturamentos = getFaturamentos(jsonArray);
        print(faturamentos);
    }

    private static JSONArray readJsonFile(String filePath) throws IOException {
        var content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONArray(content);
    }

    private static List<Double> getFaturamentos(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .filter(jsonObject -> !jsonObject.isNull("valor"))
                .map(jsonObject -> jsonObject.getDouble("valor"))
                .toList();
    }

    private static void print(List<Double> faturamentos) {
        double menorFaturamento = faturamentos.stream().min(Double::compare)
                .orElse(0.0);
        double maiorFaturamento = faturamentos.stream().max(Double::compare)
                .orElse(0.0);
        double mediaMensal = faturamentos.stream().mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        long diasAcimaDaMedia = faturamentos.stream().filter(valor -> valor > mediaMensal)
                .count();

        System.out.println("Menor valor de faturamento: " + menorFaturamento);
        System.out.println("Maior valor de faturamento: " + maiorFaturamento);
        System.out.println(
                "Número de dias com faturamento acima da média: " + diasAcimaDaMedia);
    }
}
