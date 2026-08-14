//Importação da classe Scanner para leitura da entrada
import java.util.Scanner;

//Declaração da classe Sequencia Espelho
public class SequenciaEspelho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
//Processa até o final do arquivo (EOF)
while (scanner.hasNextLine()) {
    String linha = scanner.nextLine();
    String[] numeros = linha.split(" ");
    int A = Integer.parseInt(numeros[0]);
    int B = Integer.parseInt(numeros[1]);

//Construção da sequencia crescente
StringBuilder sequenciaCrescente = new StringBuilder();
for (int i = A; i <= B; i++) {
    sequenciaCrescente.append(i);
   } 

//Construcao da sequencia espelhada
StringBuilder sequenciaEspelhada = new StringBuilder();
for (int i = sequenciaCrescente.length() - 1; i >= 0; i--) {
    sequenciaEspelhada.append(sequenciaCrescente.charAt(i));
}

//Impressao da sequencia espelho
System.out.println(sequenciaCrescente.toString() + sequenciaEspelhada.toString());
    }

scanner.close();
  }
}