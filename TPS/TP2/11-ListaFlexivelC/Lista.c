#include <stdio.h>
#include <stdlib.h>
#include "Lista.h"

void inicializarLista(Lista *lista) {
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}

Celula *novaCelula(Veiculo veiculo) {
    Celula *celula = (Celula *) malloc(sizeof(Celula));

    celula->elemento = veiculo;
    celula->prox = NULL;

    return celula;
}

void inserirInicio(Lista *lista, Veiculo veiculo) {
    Celula *nova = novaCelula(veiculo);

    nova->prox = lista->primeiro;
    lista->primeiro = nova;

    if (lista->ultimo == NULL) {
        lista->ultimo = nova;
    }

    lista->tamanho++;
}

void inserirFim(Lista *lista, Veiculo veiculo) {
    Celula *nova = novaCelula(veiculo);

    if (lista->ultimo == NULL) {
        lista->primeiro = nova;
        lista->ultimo = nova;
    } else {
        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }

    lista->tamanho++;
}

void inserir(Lista *lista, Veiculo veiculo, int posicao) {

    if (posicao <= 0) {
        inserirInicio(lista, veiculo);
        return;
    }

    if (posicao >= lista->tamanho) {
        inserirFim(lista, veiculo);
        return;
    }

    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }

    Celula *nova = novaCelula(veiculo);

    nova->prox = anterior->prox;
    anterior->prox = nova;

    lista->tamanho++;
}

Veiculo removerInicio(Lista *lista) {

    Celula *tmp = lista->primeiro;
    Veiculo removido = tmp->elemento;

    lista->primeiro = tmp->prox;

    if (lista->primeiro == NULL) {
        lista->ultimo = NULL;
    }

    free(tmp);
    lista->tamanho--;

    return removido;
}

Veiculo removerFim(Lista *lista) {

    if (lista->tamanho == 1) {
        return removerInicio(lista);
    }

    Celula *anterior = lista->primeiro;

    while (anterior->prox != lista->ultimo) {
        anterior = anterior->prox;
    }

    Veiculo removido = lista->ultimo->elemento;

    free(lista->ultimo);

    lista->ultimo = anterior;
    lista->ultimo->prox = NULL;

    lista->tamanho--;

    return removido;
}

Veiculo remover(Lista *lista, int posicao) {

    if (posicao <= 0) {
        return removerInicio(lista);
    }

    if (posicao >= lista->tamanho - 1) {
        return removerFim(lista);
    }

    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }

    Celula *tmp = anterior->prox;
    Veiculo removido = tmp->elemento;

    anterior->prox = tmp->prox;

    free(tmp);
    lista->tamanho--;

    return removido;
}

void mostrarLista(Lista *lista) {

    Celula *atual = lista->primeiro;
    char buffer[500];

    while (atual != NULL) {
        formatVeiculo(atual->elemento, buffer);
        printf("%s\n", buffer);

        atual = atual->prox;
    }
}

void liberarLista(Lista *lista) {

    Celula *atual = lista->primeiro;

    while (atual != NULL) {
        Celula *tmp = atual;
        atual = atual->prox;
        free(tmp);
    }

    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}
