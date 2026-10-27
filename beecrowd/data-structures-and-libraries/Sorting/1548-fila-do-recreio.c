#include <stdio.h>

/* Criação de função do Insertion Sort no vetor
que será ordenado na ordem decrescente.*/
void insertionSort(int vet[], int n) {
    int chave, i, j;
    for(i = 1; i < n; i++) {
    chave = vet[i];
    j = i - 1;

    while (j >= 0 && vet[j] < chave) {
        vet[j + 1] = vet[j];
        j--;
    }

    vet[j + 1] = chave;
}
}

int main() {
int n;

scanf("%d", &n);

// Verifica as posições em ordem decrescente.
while (n--) {
    int m;
    scanf("%d", &m);
    int original[m];
    int ordenado[m];

// Leitura das notas e cópia para o vetor que será ordenado.
for(int i = 0; i < m; i++) {
    // Faz a leitura de notas[i].
    scanf("%d", &original[i]); 
    // Cópia preservando as notas originais.
    ordenado[i] = original[i];
}
    // Reordena pela nota (decrescente).
    insertionSort(ordenado, m);

    /* Compara original[i] com notas[i], 
    para verificar se a nota que estava 
    naquela posição continua na mesma posição.*/
    int count = 0; 
    for (int i = 0; i < m; i++) {
        // Compara as posições.
        if(original[i] == ordenado[i]) {
            count++;
        }
    }

    // Imprime o resultado.
    printf("%d\n", count);
}
}