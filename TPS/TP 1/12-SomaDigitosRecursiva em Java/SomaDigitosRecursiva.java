public class SomaDigitosRecursiva {

    // Método público: trata sinal negativo antes de iniciar a recursão
    public static int somaDigitos(int num) {
        // Garante que trabalhamos apenas com positivos na recursão
        if (num < 0) {
            num = -num;
        }
        return somaDigitos(num, 0);
    }

    // Método privado/recursivo: acumula a soma
    private static int somaDigitos(int num, int soma) {
        int resp;

        // Caso base: não há mais dígitos para processar
        if (num == 0) {
            resp = soma;
        } else {
            // Passo recursivo: extrai último dígito e avança
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