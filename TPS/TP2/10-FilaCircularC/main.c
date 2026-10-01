#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LeitorCsvFunctions.h"

#define CAMINHO_DATASET "../01-ModelagemJava/veiculos.csv"
#define TAMANHO_FILA 5
#define BUFFER_SAIDA 500

typedef struct {
    Veiculo array[TAMANHO_FILA];
    int inicio;
    int tamanho;
} Fila;

// Inicializa a fila.
void inicializar(Fila *fila) {
    fila->inicio = 0;
    fila->tamanho = 0;
}

// Remove o primeiro elemento da fila.
Veiculo remover(Fila *fila) {
    Veiculo removido = fila->array[fila->inicio];

    fila->inicio = (fila->inicio + 1) % TAMANHO_FILA;
    fila->tamanho--;

    return removido;
}

// Insere no final da fila circular.
void inserir(Fila *fila, Veiculo veiculo) {
    int posicao = (fila->inicio + fila->tamanho) % TAMANHO_FILA;

    fila->array[posicao] = veiculo;
    fila->tamanho++;
}

// Procura um veículo pelo ID.
Veiculo* buscarVeiculo(Veiculo *frota, int n, int id) {
    for (int i = 0; i < n; i++) {
        if (frota[i].id == id) {
            return &frota[i];
        }
    }

    return NULL;
}

// Imprime um veículo removido.
void imprimirRemovido(Veiculo veiculo) {
    printf("(R)%s %s\n", veiculo.marca, veiculo.modelo);
}

int main() {

    int n = 0;

    // Carrega o dataset.
    Veiculo *frota = lerCsv(CAMINHO_DATASET, &n);

    if (!frota || n == 0) {
        printf("Nenhum veiculo carregado.\n");
        return 1;
    }

    Fila fila;
    inicializar(&fila);

    int id;

    /*
     * Lê os IDs iniciais.
     * Como a fila possui capacidade 5, quando estiver cheia,
     * remove o primeiro antes de inserir o próximo.
     */
    while (scanf("%d", &id) == 1) {

        if (id == -1) {
            break;
        }

        Veiculo *veiculo = buscarVeiculo(frota, n, id);

        if (veiculo != NULL) {

            if (fila.tamanho == TAMANHO_FILA) {
                Veiculo removido = remover(&fila);
                imprimirRemovido(removido);
            }

            inserir(&fila, *veiculo);
        }
    }

    // Quantidade de comandos.
    int quantidadeComandos;
    scanf("%d", &quantidadeComandos);

    char comando;

    for (int i = 0; i < quantidadeComandos; i++) {

        scanf(" %c", &comando);

        if (comando == 'R') {

            Veiculo removido = remover(&fila);
            imprimirRemovido(removido);

        } else if (comando == 'I') {

            scanf("%d", &id);

            Veiculo *veiculo = buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {

                if (fila.tamanho == TAMANHO_FILA) {
                    Veiculo removido = remover(&fila);
                    imprimirRemovido(removido);
                }

                inserir(&fila, *veiculo);
            }
        }
    }

    // Mostra os elementos restantes da fila.
    char buffer[BUFFER_SAIDA];

    for (int i = 0; i < fila.tamanho; i++) {

        int posicao = (fila.inicio + i) % TAMANHO_FILA;

        formatVeiculo(fila.array[posicao], buffer);
        printf("%s\n", buffer);
    }

    free(frota);

    return 0;
}