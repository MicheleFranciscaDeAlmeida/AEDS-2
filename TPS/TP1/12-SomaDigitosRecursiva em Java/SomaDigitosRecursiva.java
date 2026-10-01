public class SomaDigitosRecursiva {

    // Método público que inicia a recursão
    public static int somaDigitos(int num) {
        // Trata números negativos
        if (num < 0) {
            num = -num;
        }
        return somaDigitos(num, 0);
    }

    // Método recursivo que acumula a soma dos dígitos
    private static int somaDigitos(int num, int soma) {
        int resp;

        // Caso base
        if (num == 0) {
            resp = soma;
        } else {
            // Soma o último dígito e reduz o número
            resp = somaDigitos(num / 10, soma + (num % 10));
        }

        return resp;
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            System.out.println(somaDigitos(num));
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