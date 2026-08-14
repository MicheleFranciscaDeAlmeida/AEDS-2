/*Imprimir números em sequência é uma tarefa relativamente simples. Mas, e quando se trata de uma sequência espelho?
Uma sequência espelho é construída a partir de dois números inteiros, que indicam o início e o fim de uma sequência. 
Inicialmente, todos os números desse intervalo, incluindo os extremos, são escritos em ordem crescente e sem espaços. 
Em seguida, toda a sequência obtida é repetida de forma invertida, como se fosse refletida em um espelho.
Por exemplo, para os valores 7 e 12, a primeira parte da sequência é:
789101112
Ao espelhá-la, o resultado será:
789101112211101987
Escreva um programa que, dados dois números inteiros, imprima a respectiva sequência espelho.
Entrada
A entrada contém diversos casos de teste e deve ser processada até o final do arquivo (EOF).
Cada linha contém dois números inteiros A e B, separados por um espaço, representando, respectivamente, o início e o fim da sequência.
Considere que A ≤ B.
Saída
Para cada caso de teste, imprima uma linha contendo a sequência espelho correspondente.
Entradas e saídas
Entrada
1 1 5
2 10 13
3 98 101
4
Saída
1 1234554321
2 1011121331211101
3 98991001011010019989
4
Faça o programa na linguagem JAVA
*/

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
sequenciaEspelhada.reverse();

//Impressao da sequencia espelho
System.out.println(sequenciaCrescente.toString() + sequenciaEspelhada.toString());
    }

scanner.close();
  }
}