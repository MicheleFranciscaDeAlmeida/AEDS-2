import java.util.Scanner;

public class IS {

    // Verifica se a String corresponde ao FIM da entrada.
public static boolean isFim(String s) {
    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
}

// Verifica se a String é composta somente por vogais.
public static boolean isVogais(String s) {
    boolean resultado = true;
    int contLetras = 0;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        // Verifica se o caractere é uma vogal.
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            contLetras++;

        // Verifica se o caractere é uma consoante ou um caractere acentuado (não ASCII).
        } else if ((c >= 'a' && c <= 'z') ||
                   (c >= 'A' && c <= 'Z') || c > 127) {
            resultado = false;
            break;
        }
    }

    // Verifica se existe pelo menos uma letra válida na string.
    if (contLetras == 0) resultado = false;

    return resultado;
}

// Verifica se a String é composta somente por consoantes.
public static boolean isConsoantes(String s) {
    boolean resultado = true;
    int contLetras = 0;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        // Verifica se o caractere é uma letra.
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) { 
            contLetras++;

            // Verifica se a letra é uma vogal.
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                resultado = false;
                break;
            }
        }
    }

    // Verifica se existe pelo menos uma letra na string.
    if (contLetras == 0) {
        resultado = false;
    }

    return resultado;
}

// Verifica se a String representa um número inteiro.
public static boolean isInteiro(String s) {
    if (s.length() == 0) return false;
    
    boolean resultado = true;
    int inicio = 0;
    
    // Verifica se o número possui sinal negativo.
    if (s.charAt(0) == '-') {
        inicio = 1;
        if (s.length() == 1) resultado = false;
    }

    // Verifica se todos os caracteres restantes são dígitos.
    for (int i = inicio; i < s.length(); i++) {
        char c = s.charAt(i);

        if (!(c >= '0' && c <= '9')) {
            resultado = false;
            break;
        }
    }

    return resultado;
}

// Verifica se a String representa um número real.
public static boolean isReal(String s) {
    if (s.length() == 0) return false;

    boolean resultado = true;
    int inicio = 0;
    int pontosE_virgulas = 0;

    // Verifica se o número possui sinal negativo.
    if (s.charAt(0) == '-') {
        inicio = 1;
        if (s.length() == 1) return false;
    }

    for (int i = inicio; i < s.length(); i++) {
        char c = s.charAt(i);

        // Conta os separadores decimais encontrados.
        if (c == '.' || c == ',') {
            pontosE_virgulas++;

        // Verifica se o caractere é diferente de um dígito.
        } else if (!(c >= '0' && c <= '9')) {
            resultado = false;
            break;
        }
    }

    // Verifica se existe no máximo um separador decimal.
    if (pontosE_virgulas > 1) {
        resultado = false;
    }

    return resultado;
}


public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();

    while (!isFim(s)) {
        System.out.print(isVogais(s) ? "SIM " : "NAO ");
        System.out.print(isConsoantes(s) ? "SIM " : "NAO ");
        System.out.print(isInteiro(s) ? "SIM " : "NAO ");
        System.out.println(isReal(s) ? "SIM" : "NAO");

        s = sc.nextLine();
    }

    sc.close();
}

}

/*
 * Uso de IA:
 * A ferramenta de Inteligência Artificial foi utilizada como apoio
 * para fundamentação, documentação e compreensão do enunciado,
 * auxiliando na análise da lógica e na revisão do código.
 *
 * A implementação foi desenvolvida a partir do meu próprio
 * raciocínio e entendimento do problema, com testes e validação
 * realizados por mim.
 */