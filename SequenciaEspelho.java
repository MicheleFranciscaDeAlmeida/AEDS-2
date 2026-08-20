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
String sequenciaCrescente = "";
for (int i = A; i <= B; i++) {
    sequenciaCrescente += i;
   } 

//Construcao da sequencia espelhada
String sequenciaEspelhada = "";
for (int i = sequenciaCrescente.length() - 1; i >= 0; i--) {
    sequenciaEspelhada += sequenciaCrescente.charAt(i);
}

//Impressao da sequencia espelho
System.out.println(sequenciaCrescente + sequenciaEspelhada);
    }

scanner.close();
  }
}

/*
Uso de IA: ChatGPT utilizado como ferramenta de apoio ao estudo,
esclarecimento de dúvidas, pesquisa de documentação e depuração.
A implementação e as decisões sobre o código foram realizadas pela aluna.
*/