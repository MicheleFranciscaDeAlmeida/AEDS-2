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