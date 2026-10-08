#include "LeitorCsvFunctions.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINHA 500
#define CAPACIDADE_INICIAL 1000

Veiculo* lerCsv(char* caminhoArquivo, int* n) {
    FILE* arquivo = fopen(caminhoArquivo, "r");
    if (!arquivo) {
        fprintf(stderr, "Erro: Nao foi possivel abrir o arquivo %s\n", caminhoArquivo);
        *n = 0;
        return NULL;
    }

    int capacidade = CAPACIDADE_INICIAL;
    Veiculo* veiculos = (Veiculo*)malloc(capacidade * sizeof(Veiculo));
    if (!veiculos) { fclose(arquivo); *n = 0; return NULL; }

    char linha[MAX_LINHA];
    int count = 0;
    bool primeiraLinha = true;

    while (fgets(linha, MAX_LINHA, arquivo) != NULL) {
        size_t len = strlen(linha);
        if (len > 0 && linha[len - 1] == '\n') linha[len - 1] = '\0';

        if (primeiraLinha) { primeiraLinha = false; continue; }
        if (strlen(linha) == 0) continue;

        // Aumenta a capacidade do vetor quando todos os espaços disponiveis foram ocupados.
        if (count >= capacidade) {
            capacidade *= 2;
            Veiculo* temp = (Veiculo*)realloc(veiculos, capacidade * sizeof(Veiculo));
            if (!temp) { free(veiculos); fclose(arquivo); *n = 0; return NULL; }
            veiculos = temp;
        }

        // Converte a linha do CSV em um Veiculo.
        Veiculo* vPtr = parseVeiculo(linha);
        if (vPtr != NULL) {
            veiculos[count] = *vPtr;
	    
	    // Libera o malloc interno do parseVeiculo
            free(vPtr);
            count++;
        }
    }

    fclose(arquivo);

    if (count < capacidade) {
        Veiculo* temp = (Veiculo*)realloc(veiculos, count * sizeof(Veiculo));
        if (temp) veiculos = temp;
    }

    *n = count;
    return veiculos;
}
