public class InversaoStringRecursiva {

    // Método que inicia a recursividade
    public static void inverterString(String s) {
        inverterString(s, 0);
    }

    // Método recursivo que controla o índice
    private static void inverterString(String s, int i) {
        // Caso base: chegou ao fim da string
        if (i < s.length()) {
            // Chama recursivamente até o fim da string
            inverterString(s, i + 1);

            // Imprime os caracteres na ordem inversa
            System.out.print(s.charAt(i));
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        String s = sc.nextLine();

        while (!isFim(s)) {
            inverterString(s);
            System.out.println();
            s = sc.nextLine();
        }
        sc.close();
    }

    // Verifica se a entrada é "FIM"
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && 
                s.charAt(1) == 'I' && s.charAt(2) == 'M');
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