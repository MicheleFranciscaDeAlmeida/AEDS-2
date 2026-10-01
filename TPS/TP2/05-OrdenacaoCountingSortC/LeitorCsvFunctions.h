#ifndef LEITOR_CSV_FUNCTIONS_H
#define LEITOR_CSV_FUNCTIONS_H
#include "Veiculo.h"

// Faz a leitura dos veiculos do arquivo do CSV e retorna um vetor de Veiculo.
Veiculo* lerCsv(char* caminhoArquivo, int* n);

#endif
