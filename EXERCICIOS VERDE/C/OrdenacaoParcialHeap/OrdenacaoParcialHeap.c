#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int premios;
    int ano;
    char titulo[101];
} Filme;

// Funcao de comparacao pra determinar se filme a devera vir antes de filme b
// Retorna 1 se a deve vir antes e 0 caso contrario
int compara(Filme a, Filme b) {

    // Criterio 1 - mais premios primeiro
    if (a.premios != b.premios) {
        return a.premios > b.premios;
    }

    // Criterio 2 - Filme mais antigo primeiro (menor ano)
    if (a.ano != b.ano) {
        return a.ano < b.ano;
    }

    // Criterio 3 - Ordem alfabetica do titulo
    return strcmp (a.titulo, b.titulo) < 0;

    }

// Troca dois filmes
void troca(Filme *a , Filme *b) {
    Filme temp = *a;
    *a = *b;
    *b = temp;
}

// Heapify pra manter a propriedade do max-heap, pq o maior elemento fica no topo
void heapify(Filme arr[], int n, int i) {
    int maior = i; 
    int esq = 2 * i + 1; 
    int dir = 2 * i + 2; 

    // Verifica se o filho esq e maior que o atual
    if (esq < n && compara(arr[esq], arr[maior])) {
        maior = esq;
    }

    // Verifica se o filho direito e maior que o atual
    if (dir < n && compara(arr[dir], arr[maior])) {
        maior = dir;
    }

    // Se o maior nao for a raiz, troca e continua o heapify 
    if (maior != i) {
        troca(&arr[i], &arr[maior]);
        heapify(arr, n, maior);
    }
}

// HeapSort parcial - extrai apenas os k maiores elementos
void heapsortParcial(Filme arr[], int n, int k) {
    int i;

    // Construcao do heap maximo
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // Extrai os k maiores elementos
    for (int i = n - 1; i >= n - k && i > 0; i--) {

        // Move a raiz maior elemento pro final
        troca(&arr[0], &arr[i]);

        // Chama o heapify no heap reduzido
        heapify(arr, i, 0);
    }
    }

int main() {
    int N, K;

    while(scanf("%d %d", &N, &K) == 2) {
        Filme *filmes = (Filme *)malloc(N * sizeof(Filme));
        
        // Lê os dados dos filmes
        for (int i = 0; i < N; i++) {

            // Le premios e ano.
            scanf("%d %d", &filmes[i].premios, &filmes[i].ano);

            // Consome espaco antes do titulo. 
            getchar();

            //Le o restante da linha como titulo
            fgets(filmes[i].titulo, 101, stdin);
            
            // Remove o caractere de nova linha se presente
            int len = strlen(filmes[i].titulo);
            if (len > 0 && filmes[i].titulo[len - 1] == '\n') {
                filmes[i].titulo[len - 1] = '\0';
            }
        }
        
        // Aplica heapsort parcial para obter os K primeiros
        heapsortParcial(filmes, N, K);
        
        // Imprime os K primeiros filmes (que estão nas últimas K posições após o heapsort)
        for (int i = N - 1; i >= N - K; i--) {
            printf("%d %d %s\n", filmes[i].premios, filmes[i].ano, filmes[i].titulo);
        }
        
        free(filmes);
    }
    
    return 0;
}