package br.com.task;

/**
 * @author mauriciomucci
 */
public class Exemplo5 {

    /* Escreva um programa que inverta os caracteres de um string.
    IMPORTANTE:
    a) Essa string pode ser informada através de qualquer entrada de sua preferência ou pode ser
    previamente definida no código;
    b) Evite usar funções prontas, como, por exemplo, reverse;
    */
    public static void main(String[] args) {
        String str = "Hello, World! My name is Mauricio Mucci.";
        System.out.println(invertString(str));
    }

    private static String invertString(String str) {
        char[] chars = str.toCharArray();
        var inverseStr = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) inverseStr.append(chars[i]);
        return inverseStr.toString();
    }
}
