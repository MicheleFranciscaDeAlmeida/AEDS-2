import java.util.Scanner;

public class CiframentoCesar {

    // Recebe uma String e retorna uma nova String com as letras deslocadas 3 posições.
    public static String cifrar(String texto) {
    String resultado = "";
    
    // Percorre todos os caracteres da String.
    for (int i = 0; i < texto.length(); i++) {

        // Armazena em c o caractere que está sendo analisado.
        char c = texto.charAt(i);
    
    // Verifica se o caractere é uma letra minúscula.
    if (c >= 'a' && c <= 'z') {

        // Desloca a letra 3 posições, voltando ao início após o 'z'.
        c = (char) ('a' + (c - 'a' + 3) % 26);
    
    // Verifica se o caractere é uma letra maiúscula.
    } else if (c >= 'A' && c <= 'Z') {
        
        //Desloca a letra 3 posicões, voltando ao início após o 'Z'.
        c = (char) ('A' + (c - 'A' + 3) % 26);
    }

    // Adiciona o caractere processado ao resultado
    resultado += c;

    }

    return resultado;

    }
    // Faz a leitura das linhas da entrada, chama o método de ciframento e imprime cada resultado.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        // Lê e processa todas as linhas até o fim da entrada.
        while (scanner.hasNextLine()) {
            String texto = scanner.nextLine();
        
            System.out.println(cifrar(texto));
        }
        scanner.close();
    }
}