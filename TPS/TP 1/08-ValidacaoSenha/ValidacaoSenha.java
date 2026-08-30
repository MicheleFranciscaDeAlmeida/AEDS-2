import java.util.Scanner;

public class ValidacaoSenha {

    // Método iterativo conforme pedido no enunciado
    public static boolean validarSenha(String s) {
        if (s.length() < 8) {
            return false;
        }

        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temEspecial = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                temMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                temMinuscula = true;
            } else if (c >= '0' && c <= '9') {
                temNumero = true;
            } else {
                // Verifica se é um caractere especial (não é letra nem número)
                // O enunciado cita exemplos como !, @, #. 
                // Caracteres que não são letras nem números são considerados especiais.
                temEspecial = true;
            }
        }

        return temMaiuscula && temMinuscula && temNumero && temEspecial;
    }

    // Método auxiliar para verificar se a string é "FIM"
    public static boolean isFim(String s) {
        return s.equals("FIM");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // Loop de leitura até encontrar "FIM"
        while (!isFim(s)) {
            if (validarSenha(s)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
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