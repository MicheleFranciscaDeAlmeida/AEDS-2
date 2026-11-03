#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int dado;
    struct No *esq;
    struct No *dir;
} No;

No* criarNo(int valor) {
    No* novo = (No*) malloc(sizeof(No));

    novo->dado = valor;
    novo->esq = NULL;
    novo->dir = NULL;

    return novo;
}

No* inserir(No* raiz, int valor) {
    if (raiz == NULL) {
        return criarNo(valor);
    }

    if (valor < raiz->dado) {
        raiz->esq = inserir(raiz->esq, valor);
    } else {
        raiz->dir = inserir(raiz->dir, valor);
    }

    return raiz;
}

int contarFolhas(No* raiz) {
    if (raiz == NULL) {
        return 0;
    }

    if (raiz->esq == NULL && raiz->dir == NULL) {
        return 1;
    }

    return contarFolhas(raiz->esq) +
           contarFolhas(raiz->dir);
}

int contarNosUmFilho(No* raiz) {
    if (raiz == NULL) {
        return 0;
    }

    int cont = 0;

    if ((raiz->esq != NULL && raiz->dir == NULL) ||
        (raiz->esq == NULL && raiz->dir != NULL)) {
        cont = 1;
    }

    return cont + contarNosUmFilho(raiz->esq) +
                 contarNosUmFilho(raiz->dir);
}

int contarNosDoisFilhos(No* raiz) {
    if (raiz == NULL) {
        return 0;
    }

    int cont = 0;

    if (raiz->esq != NULL && raiz->dir != NULL) {
        cont = 1;
    }

    return cont + contarNosDoisFilhos(raiz->esq) +
                 contarNosDoisFilhos(raiz->dir);
}

int encontrarAscendentes(No* raiz, int valor,
                          int ascendentes[], int *indice) {
    if (raiz == NULL) {
        return 0;
    }

    if (raiz->dado == valor) {
        return 1;
    }

    if (encontrarAscendentes(raiz->esq, valor,
                             ascendentes, indice)) {
        ascendentes[*indice] = raiz->dado;
        (*indice)++;
        return 1;
    }

    if (encontrarAscendentes(raiz->dir, valor,
                             ascendentes, indice)) {
        ascendentes[*indice] = raiz->dado;
        (*indice)++;
        return 1;
    }

    return 0;
}

void liberarArvore(No* raiz) {
    if (raiz == NULL) {
        return;
    }

    liberarArvore(raiz->esq);
    liberarArvore(raiz->dir);

    free(raiz);
}

int main() {
    int n, valor, i;

    while (scanf("%d", &n) == 1) {

        No* raiz = NULL;

        for (i = 0; i < n; i++) {
            scanf("%d", &valor);
            raiz = inserir(raiz, valor);
        }

        int valorBusca;
        scanf("%d", &valorBusca);

        printf("%d\n", contarFolhas(raiz));
        printf("%d\n", contarNosUmFilho(raiz));
        printf("%d\n", contarNosDoisFilhos(raiz));

        int *ascendentes = (int*) malloc(n * sizeof(int));
        int indice = 0;

        if (encontrarAscendentes(raiz, valorBusca,
                                 ascendentes, &indice)
            && indice > 0) {

            for (i = 0; i < indice; i++) {
                if (i > 0) {
                    printf(" ");
                }

                printf("%d", ascendentes[i]);
            }

            printf("\n");

        } else {
            printf("-1\n");
        }

        free(ascendentes);
        liberarArvore(raiz);
    }

    return 0;
}