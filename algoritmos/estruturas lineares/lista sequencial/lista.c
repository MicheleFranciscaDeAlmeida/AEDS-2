#include <stdio.h>
#include <stdlib.h>

typedef struct List
{
    int *array;
    int n;
    int MAXTAM;
} List;

void startList(List *lista, int tam)
{
    lista->MAXTAM = tam;
    lista->n = 0;

    lista->array = (int *) malloc(sizeof(int) * tam);

    if(lista->array == NULL)
    {
        printf("Erro ao alocar memória.\n");
        exit(1);
    }
}

void inserirInicio(List *lista, int x)
{
    if(lista->n >= lista->MAXTAM)
    {
        printf("Lista cheia.\n");
        exit(1);
    }

    for(int i = lista->n; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = x;
    lista->n++;
}

void inserirFim(List *lista, int x)
{
    if(lista->n >= lista->MAXTAM)
    {
        printf("Lista cheia.\n");
        exit(1);
    }

    lista->array[lista->n] = x;
    lista->n++;
}

void inserir(List *lista, int pos, int x)
{
    if(lista->n >= lista->MAXTAM)
    {
        printf("Lista cheia.\n");
        exit(1);
    }

    if(pos < 0 || pos > lista->n)
    {
        printf("Posição inválida.\n");
        exit(1);
    }

    for(int i = lista->n; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = x;
    lista->n++;
}

int removerInicio(List *lista)
{
    if(lista->n == 0)
    {
        printf("Lista vazia.\n");
        exit(1);
    }

    int resp = lista->array[0];

    for(int i = 0; i < lista->n - 1; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

int removerFim(List *lista)
{
    if(lista->n == 0)
    {
        printf("Lista vazia.\n");
        exit(1);
    }

    lista->n--;

    return lista->array[lista->n];
}

int remover(List *lista, int pos)
{
    if(lista->n == 0)
    {
        printf("Lista vazia.\n");
        exit(1);
    }

    if(pos < 0 || pos >= lista->n)
    {
        printf("Posição inválida.\n");
        exit(1);
    }

    int resp = lista->array[pos];

    for(int i = pos; i < lista->n - 1; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->n--;

    return resp;
}

void mostrar(List *lista)
{
    printf("[ ");

    for(int i = 0; i < lista->n; i++)
    {
        printf("%d ", lista->array[i]);
    }

    printf("]\n");
}

void freeList(List *lista)
{
    free(lista->array);
    lista->array = NULL;
    lista->n = 0;
    lista->MAXTAM = 0;
}

int main()
{
    List lista;

    startList(&lista, 10);

    inserirFim(&lista, 10);
    inserirFim(&lista, 20);
    inserirFim(&lista, 30);

    inserirInicio(&lista, 5);

    inserir(&lista, 2, 15);

    printf("Lista após inserções:\n");
    mostrar(&lista);

    printf("Removido do início: %d\n", removerInicio(&lista));
    printf("Removido do fim: %d\n", removerFim(&lista));
    printf("Removido da posição 1: %d\n", remover(&lista, 1));

    printf("Lista após remoções:\n");
    mostrar(&lista);

    freeList(&lista);

    return 0;
}

// LISTA SEQUENCIAL

// A lista sequencial é uma estrutura de dados linear que armazena seus
// elementos em posições contíguas de memória, geralmente utilizando um array.

// Os elementos podem ser acessados diretamente através de índices, permitindo
// acesso rápido a uma determinada posição da lista.

// A inserção e a remoção de elementos podem exigir o deslocamento de vários
// elementos para manter a ordem da lista. Por exemplo, ao inserir um elemento
// no início, os elementos existentes precisam ser deslocados para a direita.

// Quando implementada utilizando um array de tamanho fixo, a lista possui uma
// capacidade máxima definida durante sua criação.

// Exemplo:

//      índice:   0    1    2    3
//              [10] [20] [30] [40]
//
// O acesso ao elemento de índice 2 é realizado diretamente:

//      array[2] -> 30

// Principais características:
// - elementos armazenados em posições contíguas;
// - acesso direto através de índices;
// - inserções e remoções podem exigir deslocamentos;
// - implementação simples utilizando arrays.