package br.com.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author mauriciomucci
 */
public class Exemplo4 {

    /* Dado o valor de faturamento mensal de uma distribuidora, detalhado por estado:</p>
   * SP – R$ 67.836,43
   * RJ – R$ 36.678,66
   * MG – R$ 29.229,88
   * ES – R$ 27.165,48
   * Outros – R$ 19.849,53
    Escreva um programa na linguagem que desejar onde calcule o percentual de representação que cada
    estado teve dentro do valor total mensal da distribuidora.
    */
    public static void main(String[] args) {
        var listDistribuidora = buildListaDistribuidora();
        print(listDistribuidora);
    }

    private static List<Distribuidora> buildListaDistribuidora() {
        List<Distribuidora> listDistribuidora = new ArrayList<>();
        listDistribuidora.add(new Distribuidora(Estado.OUTROS,
                new BigDecimal("19849.53")));
        listDistribuidora.add(new Distribuidora(Estado.ES,
                new BigDecimal("27165.48")));
        listDistribuidora.add(new Distribuidora(Estado.RJ,
                new BigDecimal("36678.66")));
        listDistribuidora.add(new Distribuidora(Estado.MG,
                new BigDecimal("29229.88")));
        listDistribuidora.add(new Distribuidora(Estado.SP,
                new BigDecimal("67836.43")));
        return listDistribuidora;
    }

    private static void print(List<Distribuidora> listDistribuidora) {
        var total = listDistribuidora.stream()
                .map(Distribuidora::faturamento)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var locale = new Locale("pt", "BR");
        listDistribuidora.stream()
                .sorted(Comparator.comparing(Distribuidora::estado))
                .forEachOrdered(distribuidora -> {
                    var porcentagem = distribuidora.faturamento().divide(total, 2,
                            RoundingMode.HALF_UP);
                    System.out.printf("Faturamento percentual do estado de %s: %s%n",
                            distribuidora.estado(),
                            NumberFormat.getPercentInstance(locale).format(porcentagem));
                });
        System.out.println("Faturamento total: "
                + NumberFormat.getCurrencyInstance(locale).format(total));
    }
}
