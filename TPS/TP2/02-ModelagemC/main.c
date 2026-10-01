#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "LeitorCsvFunctions.h"

#define CAMINHO_DATASET "../01-ModelagemJava/veiculos.csv"
#define BUFFER_SAIDA 500

int main() {
    int n = 0;

    // Carrega os veiculos do arquivo CSV.
    Veiculo* frota = lerCsv(CAMINHO_DATASET, &n);

    // Verifica se algum veiculo foi carregado.
    if (!frota || n == 0) {
        printf("Nenhum veiculo carregado.\n");
        return 1;
    }

    int idBusca;
    char buffer[BUFFER_SAIDA];

    // Faz a leitura dos IDs dos veiculos até que seja informado -1.
    while (scanf("%d", &idBusca) == 1) {
        if (idBusca == -1) break;

        bool encontrado = false;

        // Percorre a frota procurando o veiculo pelo ID.	
        for (int i = 0; i < n; i++) {
            if (frota[i].id == idBusca) {

                // Formata e exibe os dados do veiculo encontrado.
                formatVeiculo(frota[i], buffer);
                printf("%s\n", buffer); 
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            printf("Veiculo nao encontrado.\n");
        }
    }

    // Libera a memoria reservada para frota.
    free(frota);
    return 0;
}
