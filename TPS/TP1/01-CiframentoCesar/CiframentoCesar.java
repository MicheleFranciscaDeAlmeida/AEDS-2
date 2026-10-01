import java.util.Scanner;

public class CiframentoCesar {

    // Recebe uma String e retorna uma nova String com cada caractere deslocado 3 posições.
    public static String cifrar(String texto) {
    String resultado = "";

    // 
    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);
        c = (char) (c + 3);
        resultado += c;
    }

    return resultado;
}

 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Lê e processa as linhas até encontrar "FIM".
    while (scanner.hasNextLine()) {
        String texto = scanner.nextLine();

        if (!texto.equals("FIM")) {
            System.out.println(cifrar(texto));
        }
    }

    scanner.close();
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