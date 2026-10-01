#ifndef LISTA_H
#define LISTA_H

#include "Veiculo.h"

typedef struct Celula {
    Veiculo elemento;
    struct Celula *prox;
} Celula;

typedef struct {
    Celula *primeiro;
    Celula *ultimo;
    int tamanho;
} Lista;

void inicializarLista(Lista *lista);
void inserirInicio(Lista *lista, Veiculo veiculo);
void inserirFim(Lista *lista, Veiculo veiculo);
void inserir(Lista *lista, Veiculo veiculo, int posicao);
Veiculo removerInicio(Lista *lista);
Veiculo removerFim(Lista *lista);
Veiculo remover(Lista *lista, int posicao);
void mostrarLista(Lista *lista);
void liberarLista(Lista *lista);

#endif
