#include <stdio.h>

int main() {
    
    // Número de casos de teste
    int N;
    
    // Lê o número de casos de teste
    scanf("%d", &N);

    // Limpa o buffer de entrada para evitar problemas com fgets
    getchar(); 

    // Loop para processar cada caso de teste
    for (int j = 0; j < N; j++) {

        // Caracteres até 1001
        char expres[1001];

        // Lê a expressão do usuário
        fgets(expres, sizeof(expres), stdin);

        // Contador inicializa com 0
        int contador = 0;

        // Erro inicializa com 0
        int erro = 0;

        // Percorre verificando de 0 até i
        for (int i = 0; expres[i] != '\0'; i++) {
            // Condição se a expres tem parenteses
            if (expres[i] == '(') {
                // Incrementa mais um
                contador++;
            // Se não tem, não é igual
            } else if (expres[i] == ')') {
                // Decrementa menos um
                contador--;
            }

            // Condição do contador se é menor que 0
            if (contador < 0) {
                erro = 1;
                break;
            }
        }
        // Compara se for = 0 correct, se nao for, incorrect
        if (contador == 0 && erro == 0) {
            printf("correct\n");
        } else {
            printf("incorrect\n");
    }

  }
  
  return 0;
 }