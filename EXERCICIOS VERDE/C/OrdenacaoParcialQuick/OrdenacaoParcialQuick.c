#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int nota;
    int faltas;
    char nome[51];
} Aluno;

// Retorna 1 se o aluno A deve aparecer antes do aluno B.
// Critérios de ordenação:
// 1. Maior nota primeiro.
// 2. Em caso de empate, menor número de faltas primeiro.
// 3. Persistindo o empate, nome em ordem alfabética.
int compara(Aluno a, Aluno b) {
    if (a.nota != b.nota) {
        return a.nota > b.nota;
    }

    if (a.faltas != b.faltas) {
        return a.faltas < b.faltas;
    }

    return strcmp(a.nome, b.nome) < 0;
}

// Troca dois alunos de posição no vetor.
void troca(Aluno *a, Aluno *b) {
    Aluno temp = *a;
    *a = *b;
    *b = temp;
}

// Particiona o vetor utilizando o último elemento como pivô.
// Os alunos que devem aparecer antes do pivô ficam à esquerda;
// os demais ficam à direita.
int particao(Aluno arr[], int baixo, int alto) {
    Aluno pivo = arr[alto];
    int i = baixo - 1;

    for (int j = baixo; j < alto; j++) {
        if (compara(arr[j], pivo)) {
            i++;
            troca(&arr[i], &arr[j]);
        }
    }

    // Coloca o pivô na posição correta.
    troca(&arr[i + 1], &arr[alto]);

    return i + 1;
}

// Ordena parcialmente o vetor usando QuickSort.
// O objetivo é deixar os K primeiros alunos na ordem correta,
// sem precisar ordenar completamente a parte que não será impressa.
void quicksortParcial(Aluno arr[], int baixo, int alto, int k) {
    // Caso-base: não há mais de um elemento para ordenar.
    if (baixo >= alto) {
        return;
    }

    // Posiciona o pivô na posição definitiva.
    int pi = particao(arr, baixo, alto);

    // Ordena a parte esquerda, que contém elementos anteriores ao pivô.
    if (baixo < pi) {
        quicksortParcial(arr, baixo, pi - 1, k);
    }

    // Explora a parte direita somente se ela puder contribuir
    // para os K primeiros elementos.
    if (pi < k - 1 && pi < alto) {
        quicksortParcial(arr, pi + 1, alto, k);
    }
}

int main(void) {
    int N, K;

    // Lê conjuntos de dados até o fim da entrada.
    while (scanf("%d %d", &N, &K) == 2) {

        // Reserva memória para armazenar os N alunos.
        Aluno *alunos = malloc((size_t)N * sizeof(Aluno));

        if (alunos == NULL) {
            return 1;
        }

        // Primeiro: lê TODOS os alunos.
        for (int i = 0; i < N; i++) {

            // Lê a nota e o número de faltas.
            if (scanf("%d %d",
                      &alunos[i].nota,
                      &alunos[i].faltas) != 2) {
                free(alunos);
                return 1;
            }

            // Consome os espaços e as tabulações antes do nome.
            int c;

            do {
                c = getchar();
            } while (c == ' ' || c == '\t');

            if (c == EOF) {
                free(alunos);
                return 1;
            }

            // Devolve o primeiro caractere do nome para a entrada.
            ungetc(c, stdin);

            // Lê o nome completo, incluindo espaços internos.
            if (fgets(alunos[i].nome,
                      sizeof(alunos[i].nome), stdin) == NULL) {
                free(alunos);
                return 1;
            }

            // Remove as quebras de linha LF (\n) e CR (\r),
            // caso estejam presentes no final do nome.
            size_t len = strlen(alunos[i].nome);

            while (len > 0 &&
                   (alunos[i].nome[len - 1] == '\n' ||
                    alunos[i].nome[len - 1] == '\r')) {
                alunos[i].nome[--len] = '\0';
            }
        }

        // IMPORTANTE:
        // O QuickSort só deve ser executado DEPOIS que todos
        // os N alunos tiverem sido lidos e inicializados.
        quicksortParcial(alunos, 0, N - 1, K);

        // Imprime os K primeiros alunos na ordem definida.
        for (int i = 0; i < K; i++) {
            printf("%d %d %s\n",
                   alunos[i].nota,
                   alunos[i].faltas,
                   alunos[i].nome);
        }

        // Libera a memória reservada para o vetor.
        free(alunos);
    }

    return 0;
}