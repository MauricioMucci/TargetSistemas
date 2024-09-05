package br.com.task;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        String filePath = "faturamento.json"; // Caminho para o arquivo JSON
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            List<Double> faturamentos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (!jsonObject.isNull("valor")) {
                    faturamentos.add(jsonObject.getDouble("valor"));
                }
            }

            double menorFaturamento = faturamentos.stream().min(Double::compare).orElse(0.0);
            double maiorFaturamento = faturamentos.stream().max(Double::compare).orElse(0.0);
            double mediaMensal = faturamentos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            long diasAcimaDaMedia = faturamentos.stream().filter(valor -> valor > mediaMensal).count();

            System.out.println("Menor valor de faturamento: " + menorFaturamento);
            System.out.println("Maior valor de faturamento: " + maiorFaturamento);
            System.out.println("Número de dias com faturamento acima da média: " + diasAcimaDaMedia);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
