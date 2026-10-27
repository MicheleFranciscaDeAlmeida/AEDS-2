#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char nome[31];
    int horas;
    int minutos;
    int segundos;
    int ordem_original;
} Corredor;

// Converte o tempo de conclusão para segundos.
long totalSegundos(Corredor c) {
    return (long)c.horas * 3600 + (long)c.minutos * 60 + c.segundos;
}

// Retorna 1 se c1 deve aparecer antes de c2.
int ehMelhor(Corredor c1, Corredor c2) {
    long t1 = totalSegundos(c1);
    long t2 = totalSegundos(c2);

    if (t1 != t2) {
        return t1 < t2;
    }

    // Em caso de empate, preserva a ordem de entrada.
    return c1.ordem_original < c2.ordem_original;
}

int main(void) {
    int N, K;

    // Processa casos de teste até o fim da entrada (EOF).
    while (scanf("%d %d", &N, &K) == 2) {
        // Armazena apenas os K melhores corredores.
        Corredor *topK = malloc((size_t)K * sizeof(Corredor));

        if (topK == NULL) {
            return 1;
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            Corredor atual;

            if (scanf("%30s %d %d %d",
                      atual.nome,
                      &atual.horas,
                      &atual.minutos,
                      &atual.segundos) != 4) {
                free(topK);
                return 1;
            }

            atual.ordem_original = i;

            // Enquanto houver espaço, insere o corredor no topK.
            if (count < K) {
                topK[count] = atual;
                count++;
            } else {
                // Se o topK estiver cheio, só considera o corredor
                // se ele for melhor que o último colocado.
                if (!ehMelhor(atual, topK[K - 1])) {
                    continue;
                }

                topK[K - 1] = atual;
            }

            // Insertion Sort: desloca o corredor atual para a esquerda
            // enquanto ele for melhor que o elemento anterior.
            int j = count - 1;

            while (j > 0 && ehMelhor(topK[j], topK[j - 1])) {
                Corredor temp = topK[j];
                topK[j] = topK[j - 1];
                topK[j - 1] = temp;
                j--;
            }
        }

        // Imprime os K melhores na ordem correta.
        for (int i = 0; i < K; i++) {
            printf("%s %d %d %d\n",
                   topK[i].nome,
                   topK[i].horas,
                   topK[i].minutos,
                   topK[i].segundos);
        }

        free(topK);
    }

    return 0;
}