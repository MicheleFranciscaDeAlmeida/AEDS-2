// Questão 9 - Encontrar Repetidos 

#include <stdio.h>
#include <stdlib.h>

// Estruturas
typedef struct No {
    int numero;
    struct No *esq, *dir;
} No;

typedef struct CelulaMatriz {
    int numero;
    struct CelulaMatriz *prox, *inf;
} CelulaMatriz;

typedef struct CelulaLista {
    int numero;
    struct CelulaLista *prox;
} CelulaLista;

// Funções auxiliares fornecidas no enunciado
CelulaLista* novaCelula(int x) {
    CelulaLista* nova = (CelulaLista*) malloc(sizeof(CelulaLista));
    nova->numero = x;
    nova->prox = NULL;
    return nova;
}

int existeNaArvore(No* i, int x) {
    while (i != NULL) {
        if (x == i->numero) return 1;
        i = (x < i->numero) ? i->esq : i->dir;
    }
    return 0;
}

void inserirDecrescente(CelulaLista* cabeca, int x) {
    CelulaLista* i = cabeca;
    // Procura posição onde o próximo é menor que x (para manter decrescente)
    while (i->prox != NULL && i->prox->numero > x) {
        i = i->prox;
    }
    // Evita duplicatas na lista se já existir
    if (i->prox != NULL && i->prox->numero == x) return;
    
    CelulaLista* nova = novaCelula(x);
    nova->prox = i->prox;
    i->prox = nova;
}

// Solucao da questao 9
CelulaLista* encontrarRepetidos(No* raiz, CelulaMatriz* inicio) {
    CelulaLista* cabeca = novaCelula(0); // Célula cabeça sentinela
    
    for (CelulaMatriz* lin = inicio; lin != NULL; lin = lin->inf) {
        for (CelulaMatriz* j = lin; j != NULL; j = j->prox) {
            if (existeNaArvore(raiz, j->numero)) {
                inserirDecrescente(cabeca, j->numero);
            }
        }
    }
    return cabeca;
}