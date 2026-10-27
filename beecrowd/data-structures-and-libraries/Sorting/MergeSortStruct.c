#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int pessoas;
    int consumoporpessoa;
} Imovel;

// Funcao merge
void merge(Imovel *arr, int esq, int meio, int dir) {
    int n1 = meio - esq + 1; 
    int n2 = dir - meio;

    // Criacao  de arrays temporarios
    Imovel *L = (Imovel *)malloc(n1 * sizeof(Imovel));
    Imovel *R = (Imovel *)malloc(n2 * sizeof(Imovel));

    // Copiar dados pra arrays temporarios
    for (int i = 0; i < n1; i++) {
        L[i] = arr[esq + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[meio + 1 + j];
    }    

    // Merge dos arrays temporarios de volta em arr[esq e dir]
    int i = 0, j = 0, k = esq;

    while (i < n1 && j < n2) {
        if (L[i].consumoporpessoa <= R[j].consumoporpessoa) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    // Cria copia dos elementos restantes de L, caso houver
    while (i < n1) {
	arr[k] = L[i];
	i++;
	k++;
    }

    while (j < n2) {
        arr[k] = R[j]; 
        j++; 
        k++; 
    }

    free(L);
    free(R);
}

    // Funcao principal do mergesort
    void mergesort(Imovel *arr, int esq, int dir) {
        if (esq < dir) {
            int meio = esq + (dir - esq) / 2;

            // Ordenar primeira e segunda metades
            mergesort(arr, esq, meio);
            mergesort(arr, meio + 1, dir);

            // Chama merge
            merge(arr, esq, meio, dir);
        }
    }

    int main() {
        int N, caso = 1;

        while (scanf("%d", &N) == 1 && N != 0) {
            if (caso > 1) {
                printf("\n"); 
            }

            Imovel *imoveis = (Imovel *)malloc(N * sizeof(Imovel));
            long long totalconsumo = 0;
            long long totalpessoas = 0;

            // Leitura dos dados
            for (int i = 0; i < N; i++) {
                int X, Y;
                scanf("%d %d", &X, &Y);

                imoveis[i].pessoas = X;
                imoveis[i].consumoporpessoa = Y / X;

                totalconsumo += Y;
                totalpessoas += X;
            }

            // Ordenar por consumo por pessoa usando mergesort manualmente
            mergesort(imoveis, 0, N - 1);

            // Agrupar imoveis com mesmo consumo
            printf("Cidade# %d:\n", caso);

                int i = 0;
                int primeiro = 1;
            while (i < N) {
                int consumoatual = imoveis[i].consumoporpessoa;
                int somapessoas = 0; 

                // Somar todas as pessoas com o mesmo consumo
                while (i < N && imoveis[i].consumoporpessoa == consumoatual) {
                    somapessoas += imoveis[i].pessoas;
                    i++;
                }

                if (!primeiro) {
                    printf(" ");
                }

                printf("%d-%d", somapessoas, consumoatual);
                primeiro = 0;
            }

            printf("\n");

            // Calcular consumo medio 
            double consumomedio = (double)totalconsumo / totalpessoas;
            consumomedio = ((int)(consumomedio * 100)) / 100.0;

            printf("Consumo medio: %.2f m3.\n", consumomedio);

            free(imoveis);
            caso++;
        }

        return 0;
    }