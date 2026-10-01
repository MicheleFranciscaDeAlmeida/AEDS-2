#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LeitorCsvFunctions.h"
#include "Lista.h"

#define CAMINHO_DATASET "../01-ModelagemJava/veiculos.csv"

Veiculo *buscarVeiculo(Veiculo *frota, int n, int id) {

    for (int i = 0; i < n; i++) {
        if (frota[i].id == id) {
            return &frota[i];
        }
    }

    return NULL;
}

void imprimirRemovido(Veiculo veiculo) {
    printf("(R)%s %s\n", veiculo.marca, veiculo.modelo);
}

int main() {

    int n = 0;

    Veiculo *frota = lerCsv(CAMINHO_DATASET, &n);

    if (!frota || n == 0) {
        printf("Nenhum veiculo carregado.\n");
        return 1;
    }

    Lista lista;
    inicializarLista(&lista);

    int id;

    // IDs iniciais.
    while (scanf("%d", &id) == 1) {

        if (id == -1) {
            break;
        }

        Veiculo *veiculo = buscarVeiculo(frota, n, id);

        if (veiculo != NULL) {
            inserirFim(&lista, *veiculo);
        }
    }

    int quantidadeComandos;
    scanf("%d", &quantidadeComandos);

    for (int i = 0; i < quantidadeComandos; i++) {

        char comando[3];
        scanf("%s", comando);

        if (strcmp(comando, "II") == 0) {

            scanf("%d", &id);

            Veiculo *veiculo = buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserirInicio(&lista, *veiculo);
            }

        } else if (strcmp(comando, "I*") == 0) {

            int posicao;
            scanf("%d %d", &posicao, &id);

            Veiculo *veiculo = buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserir(&lista, *veiculo, posicao);
            }

        } else if (strcmp(comando, "IF") == 0) {

            scanf("%d", &id);

            Veiculo *veiculo = buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserirFim(&lista, *veiculo);
            }

        } else if (strcmp(comando, "RI") == 0) {

            Veiculo removido = removerInicio(&lista);
            imprimirRemovido(removido);

        } else if (strcmp(comando, "R*") == 0) {

            int posicao;
            scanf("%d", &posicao);

            Veiculo removido = remover(&lista, posicao);
            imprimirRemovido(removido);

        } else if (strcmp(comando, "RF") == 0) {

            Veiculo removido = removerFim(&lista);
            imprimirRemovido(removido);
        }
    }

    mostrarLista(&lista);

    liberarLista(&lista);
    free(frota);

    return 0;
}
