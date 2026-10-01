#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "LeitorCsvFunctions.h"

#define CAMINHO_DATASET "../01-ModelagemJava/veiculos.csv"

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
    Veiculo* veiculosSelecionados = (Veiculo*)malloc(n * sizeof(Veiculo));

    int quantidade = 0;

    char entrada[100];

    // Faz a leitura dos IDs dos veiculos ate encontrar -1.
    while (fgets(entrada, sizeof(entrada), stdin) != NULL) {

        entrada[strcspn(entrada, "\n")] = '\0';

        if (strcmp(entrada, "-1") == 0) {
            break;
        }

        int idBusca;

        sscanf(entrada, "%d", &idBusca);

        // Percorre a frota procurando o veiculo pelo ID.
        for (int i = 0; i < n; i++) {

            if (frota[i].id == idBusca) {

                // Armazena o veiculo encontrado no array de selecionados.
                veiculosSelecionados[quantidade] = frota[i];
                quantidade++;

                break;
            }
        }
    }

    // Ordena os veiculos selecionados pelo atributo modelo.
    selectionSort(veiculosSelecionados, quantidade);

    char modeloBusca[100];

    // Faz a leitura dos modelos ate encontrar FIM.
    while (fgets(modeloBusca, sizeof(modeloBusca), stdin) != NULL) {

        modeloBusca[strcspn(modeloBusca, "\n")] = '\0';

        if (strcmp(modeloBusca, "FIM") == 0) {
            break;
        }

        // Pesquisa o modelo usando pesquisa binaria.
        if (pesquisaBinaria(veiculosSelecionados, quantidade, modeloBusca)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    // Libera a memoria reservada.
    free(veiculosSelecionados);
    free(frota);

    return 0;
}