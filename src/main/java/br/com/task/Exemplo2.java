package br.com.task;

import java.util.Scanner;

public class Exemplo2 {

    /* Dado a sequência de Fibonacci, onde se inicia por 0 e 1 e o próximo valor sempre será a
    soma dos 2 valores anteriores (exemplo: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...),
    escreva um programa na linguagem que desejar onde, informado um número, ele calcule a
    sequência de Fibonacci e retorne uma mensagem avisando se o número informado pertence
    ou não a sequência.
    */
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Informe um número: ");
        int num = scanner.nextInt();
        scanner.close();
        System.out.println(isFibonacci(num) ?
                "%d pertence à sequência de Fibonacci.".formatted(num) :
                "%d não pertence à sequência de Fibonacci.".formatted(num));
    }

    public static boolean isFibonacci(int num) {
        int a = 0, b = 1;
        while (a < num) {
            int temp = a;
            a = b;
            b = temp + b;
        }
        return a == num;
    }
}
