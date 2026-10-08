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

    // Cria um array para armazenar os veiculos informados na entrada.
    Veiculo* veiculosSelecionados =
        (Veiculo*)malloc(n * sizeof(Veiculo));

    int quantidade = 0;

    int idBusca;
    char buffer[BUFFER_SAIDA];

    // Faz a leitura dos IDs ate que seja informado -1.
    while (scanf("%d", &idBusca) == 1) {

        if (idBusca == -1) {
            break;
        }

        bool encontrado = false;

        // Percorre a frota procurando o veiculo pelo ID.
        for (int i = 0; i < n; i++) {

            if (frota[i].id == idBusca) {

                // Armazena o veiculo encontrado.
                veiculosSelecionados[quantidade] = frota[i];
                quantidade++;

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            printf("Veiculo nao encontrado.\n");
        }
    }

    // Ordena os veiculos selecionados pelo modelo.
    selectionSort(veiculosSelecionados, quantidade);

    // Imprime os veiculos depois da ordenacao.
    for (int i = 0; i < quantidade; i++) {

        formatVeiculo(veiculosSelecionados[i], buffer);
        printf("%s\n", buffer);
    }

    // Libera a memoria reservada.
    free(veiculosSelecionados);
    free(frota);

    return 0;
}