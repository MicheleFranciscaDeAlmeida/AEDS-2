import java.util.Scanner;
import java.util.Random;

public class AlteracaoAleatoria {

    // Faz o sorteio das duas letras minusculas
    static Random gerador = new Random();

    // Recebe uma String, sorteia duas letras minusculas e substitui todas as ocorrencias
    // da primeira pela segunda
    public static String Alterar(String texto) {
    String resultado = "";

    // Sorteia a letra1
    char letra1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));

    // Sorteia a letra2
    char letra2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26 ))); 
    
    // Percorre a String caractere por caractere
    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);

        if (c == letra1) {
            c = letra2;
        }
        resultado += c;
    }
    return resultado;
    }

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Define a semente do gerador de numeros aleatorios
    gerador.setSeed(4);
    
    String texto = scanner.nextLine();
    
    // Processa as linhas ate encontrar "FIM"
    while (!texto.equals("FIM")) {
        System.out.println(Alterar(texto));

        texto = scanner.nextLine();
      }
    scanner.close();
   }
}