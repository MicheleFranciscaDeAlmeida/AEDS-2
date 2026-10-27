/*uma plataforma de programacao competitiva deseja organizar a classificacao dos participantes de uma competicao. cada 
competidor possui uma pontuacao total, uma quantidade de problemas resolvidos e um nome.
a classificacao devera seguir os seguintes criterios nesta ordem: 
pontuacao: competidores com maior pontuacao devem aparecer primeiro;
problemas resolvidos: em caso de empate na pontuacao, competidores com maior quantidade de problemas resolvidos devem aparecer primeiro.
empate completo: caso os dois criterios sejam iguais, a ordem original dos competidores devera ser preservada.
vc devera implementar o algoritmo Mergesort, em c, pra realizar a ordenacao. Nao eh permitido utilizar outros algoritmos de ordenacao ou 
funcoes prontas de ordenacao.

entrada
a entrada contem os registros dos competidores e termina com o fim do arquivo eof. cada linha contem pontuacao, quantidade de problemas 
resolvidos, o nome do competidor, separados por espacos. a pontuacao e a quantidade de problemas resolvidos e o nome do competidor,
separados por espacos. a pontuacao e a quantidade de problemas resolvidos sao numeros inteiros nao negativos. o nome possui no maximo 100
caracteres e pode conter espacos. a entrada contem no maximo 1000 competidores. 

saida 
imprima todos os competidores classificados de acordo com os criterios estabelecidos. cada competidor devera ser apresentado em uma linha
contendo a pontuacao, a quantidade de problemas resolvidos e o nome, no mesmo formato de entrada.
exemplo
entrada 
850 6 Ana Paula
1200 4 Carlos Eduardo 
850 12 Beatriz Souza
600 9 Daniel Lima
1200 15 Fernanda Costa
850 6 Gabriel Rocha
saida 
1200 15 Fernanda Costa 
1200 4 Carlos Eduardo
850 12 Beatriz Souza 
850 6 Ana Paula
850 6 Gabriel Rocha 
600 9 Daniel Lima
*/


#include <stdio.h>
#include <string.h>

#define MAX 1000
#define TAM_NOME 101

typedef struct {
    int pontuacao;
    int problemas;
    char nome[TAM_NOME];
} Competidor;

Competidor competidores[MAX];
Competidor auxiliar[MAX];

int comparar(Competidor a, Competidor b) {
    if (a.pontuacao != b.pontuacao) {
        return a.pontuacao > b.pontuacao;
    }

    if (a.problemas != b.problemas) {
        return a.problemas > b.problemas;
    }

    // Mantem a ordem original em caso de empate
    return 1; 
}

void merge(int inicio, int meio, int fim) {
    int i = inicio;
    int j = meio + 1;
    int k = inicio;

    while (i <= meio && j <= fim) {
        if (comparar(competidores[i], competidores[j])) {
            auxiliar[k++] = competidores[i++];
        } else {
            auxiliar[k++] = competidores[j++];
        }
    }

    while (i <= meio) {
        auxiliar[k++] = competidores[i++];
    }

    while (j <= fim) {
        auxiliar[k++] = competidores[j++];
    }

    for (i = inicio; i <= fim; i++) {
        competidores[i] = auxiliar[i];
    }
}

void mergeSort(int inicio, int fim) {
    if (inicio >= fim) {
        return;
    }

    int meio = inicio + (fim - inicio) / 2;

    mergeSort(inicio, meio);
    mergeSort(meio + 1, fim);
    merge(inicio, meio, fim);
}

int main(void) {
    int n = 0;

    while (n < MAX &&
           scanf("%d %d", &competidores[n].pontuacao,
                          &competidores[n].problemas) == 2) {

        int c;

        // // Ignora os espacos antes do nome
        while ((c = getchar()) == ' ' || c == '\t') {
            
        }

        if (c == '\n' || c == EOF) {
            competidores[n].nome[0] = '\0';
        } else {
            ungetc(c, stdin);
            if (fgets(competidores[n].nome,
                      TAM_NOME, stdin) == NULL) {
                break;
            }

            competidores[n].nome[
                strcspn(competidores[n].nome, "\r\n")
            ] = '\0';
        }

        n++;
    }

    if (n > 0) {
        mergeSort(0, n - 1);
    }

    for (int i = 0; i < n; i++) {
        printf("%d %d %s\n",
               competidores[i].pontuacao,
               competidores[i].problemas,
               competidores[i].nome);
    }

    return 0;
}