#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int numero;
    char nome[31];
} Predio;

int main() {
    int N;

    while (scanf("%d", &N) != EOF) {
        Predio predios[N];

        // Leitura
        for (int i = 0; i < N; i++) {
            scanf("%d %30s", &predios[i].numero, predios[i].nome);
        }

        // Selection Sort por nome
        for (int i = 0; i < N - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < N; j++) {
                if (strcmp(predios[j].nome, predios[menor].nome) < 0) {
                    menor = j;
                }
            }

            // Troca
            if (menor != i) {
                Predio temp = predios[i];
                predios[i] = predios[menor];
                predios[menor] = temp;
            }
        }

        // Imprime os prédios ordenados
        for (int i = 0; i < N; i++) {
            printf("%s %d\n", predios[i].nome, predios[i].numero);
        }

        // Consultas
        int P;
        scanf("%d", &P);

        for (int i = 0; i < P; i++) {
            char consulta[31];
            scanf("%30s", consulta);

            for (int j = 0; j < N; j++) {
                if (strcmp(consulta, predios[j].nome) == 0) {
                    printf("%s %d\n", predios[j].nome, predios[j].numero);
                    break;
                }
            }
        }
    }

    return 0;
}