/* Questão2 - Transporte de egressos
em comemoracao aos 40 anos de curso, a universidade disponibilizara onibus pra transportar os egressos
até o local do evento.
os egressos aguardam na ordem em que chegaram, quando um onibus chega, embarcam os primeiros da fila, ate o limite de lugares disponiveis, 
se houver mais lugares que pessoas aguardando, todos embarcam.
entrada
a entrada contem diversos casos de teste e termina com o fim do arquivo EOF.
cada caso comeca com um inteiro N (1 <= N <= 10 elevado a 4), seguido de N operacoes:
CHEGA nome ONIBUS C
CHEGA adiciona o egresso ao final da fila. O nome possui no maximo 30 caracteres, contem apenas letras e nao contem espacos.
ONIBUS C indica a chegada de um onibus com C lugares disponiveis (C > 0). Embarcam ate C egressos respeitando a ordem de chegada.
cada caso comeca sem egressos aguardando.

saida 

apos cada operacao ONIBUS, imprima em uma unica linha os nomes dos egressos que permaneceram aguardando,
respeitando a ordem em que estao na fila e separados por um unico espaco.
Caso nao reste nenhum egresso aguardando apos o embarque, imprima: VAZIA.

entrada 
10
CHEGA Ana
CHEGA Bruno
CHEGA Carlos
CHEGA Daniela
ONIBUS 2
CHEGA Eduardo
CHEGA Fernanda
ONIBUS 3
CHEGA Gabriel
ONIBUS 4

SAIDA
Carlos Daniela
Fernanda
VAZIA
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // O try-with-resources fecha o Scanner automaticamente
        try (Scanner sc = new Scanner(System.in)) {

            // Lê vários casos de teste até chegar ao EOF
            while (sc.hasNextInt()) {

                // Quantidade de operações
                int N = sc.nextInt();

                // Vetor que representa a fila
                String[] fila = new String[N];

                // Índice do primeiro egresso que ainda está na fila
                int inicio = 0;

                // Índice da próxima posição disponível
                int fim = 0;

                // Processa as N operações
                for (int i = 0; i < N; i++) {

                    // Lê o tipo da operação
                    String operacao = sc.next();

                    // Operação para adicionar um egresso à fila
                    if (operacao.equals("CHEGA")) {

                        // Lê o nome e coloca no final da fila
                        fila[fim] = sc.next();
                        fim++;
                    }

                    // Operação para chegada de um ônibus
                    else if (operacao.equals("ONIBUS")) {

                        // Quantidade de lugares disponíveis no ônibus
                        int C = sc.nextInt();

                        // Os primeiros C egressos embarcam
                        inicio += C;

                        // Se o ônibus tiver mais lugares que pessoas,
                        // todos os egressos embarcam
                        if (inicio > fim) {
                            inicio = fim;
                        }

                        // Verifica se a fila ficou vazia
                        if (inicio == fim) {
                            System.out.println("VAZIA");
                        }

                        // Ainda existem pessoas aguardando
                        else {

                            // Percorre os egressos que permaneceram na fila
                            for (int j = inicio; j < fim; j++) {

                                // Coloca espaço entre os nomes, mas não antes do primeiro
                                if (j > inicio) {
                                    System.out.print(" ");
                                }

                                System.out.print(fila[j]);
                            }

                            // Quebra de linha após imprimir a fila
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}