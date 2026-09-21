#include "Veiculo.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>

// Substitui virgulas por pontos para permitir a conversao de valores decimais.
void replaceCommaDot(char* str) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == ',') str[i] = '.';
    }
}

// Converte uma linha do CSV em uma estrutura Veiculo.
Veiculo* parseVeiculo(char* s) {
    if (s == NULL || strlen(s) == 0) return NULL;
    
    // Reserva memoria para copia da string e copia a linha recebida.
    char* linha = (char*)malloc(strlen(s) + 1);

    if (!linha) {
	    return NULL;
    }
	    strcpy(linha, s);
    // Reserva memoria para armazenar o veiculo.
    Veiculo* v = (Veiculo*)malloc(sizeof(Veiculo));
    if (!v) {
       free(linha);
       return NULL;
    }
    
    // Inicializa os campos do veiculo com zero.
    memset(v, 0, sizeof(Veiculo));
    
    // Separa a linha do CSV em campos usando a virgula como delimitador.
    char* token = strtok(linha, ",");
    int campo = 0;
    
    // Percorre os campos da linha e armazena cada um no atributo correspondente.
    while (token != NULL && campo < 15) {
        
	// Remove espacos no inicio e no final do campo.
        char* start = token;
        
	while (*start == ' ') start++;
        
	char* end = start + strlen(start) - 1;
        
	while (end > start && *end == ' ') { 
		*end = '\0';
	       	 end--;
       	}
        
        // Converte cada campo do CSV para o tipo e atributo correspondente.
        switch(campo) {
            case 0: v->id = atoi(start); break;
            case 1: strncpy(v->marca, start, MAX_MARCA-1); break;
            case 2: strncpy(v->modelo, start, MAX_MODELO-1); break;
            case 3: v->ano = atoi(start); break;
            case 4: strncpy(v->categoria, start, MAX_CATEGORIA-1); break;
            
	    // Armazena o combustivel no primeiro elemento do vetor de combustiveis.
            case 5:
                v->quantidadeCombustiveis = 1;
		strncpy(v->combustivel[0], start, MAX_TAM_COMBUSTIVEL - 1);break;
            case 6: v->cilindros = atoi(start); break;
            case 7: replaceCommaDot(start); v->cilindrada = atof(start); break;
            case 8: strncpy(v->transmissao, start, MAX_TRANSMISSAO-1); break;
            case 9: strncpy(v->tracao, start, MAX_TRACAO-1); break;
            case 10: replaceCommaDot(start); v->consumoCidade = atof(start); break;
            case 11: replaceCommaDot(start); v->consumoEstrada = atof(start); break;
            case 12: replaceCommaDot(start); v->co2 = atof(start); break;
            case 13: v->turbo = (strcmp(start, "true") == 0); break;
            case 14: v->dataRegistro = parseData(start); break;
        }
        
        campo++;

        token = strtok(NULL, ",");
    }

    free(linha);

    return v;
}

// Formata os dados do veiculo em uma unica string.
void formatVeiculo(Veiculo v, char* buffer) {
    
    // Monta a representacao dos combustiveis em uma unica string.
    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {
        strcat(combStr, v.combustivel[i]);
        if (i < v.quantidadeCombustiveis - 1) strcat(combStr, ", ");
    }
    
    // Converte a data para o formato de saida.
    char dataStr[15];
    formatData(v.dataRegistro, dataStr);

    // Formata todos os atributos do veiculo conforme o padrao de saida.
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.2f ## %s ## %s]",
        v.id, v.marca, v.modelo, v.ano, v.categoria, combStr,
        v.cilindros, v.cilindrada, v.transmissao, v.tracao,
        v.consumoCidade, v.consumoEstrada, v.co2,
        v.turbo ? "true" : "false", dataStr);
}

void countingSort(Veiculo* veiculos, int n) {
	if (n <= 1) {
		return;
	}

	int maior = veiculos[0].cilindros;

	for (int i = 1; i < n; i++) {
		if (veiculos[i].cilindros > maior) {
			maior = veiculos[i].cilindros;
		}
	}

	int* contagem = calloc(maior + 1, sizeof(int));
	Veiculo* ordenados = malloc(n * sizeof(Veiculo));

	for (int i = 0; i < n; i++) {
		contagem[veiculos[i].cilindros]++;
	}

	int posicao = 0;

	for (int cilindros = 0; cilindros <= maior; cilindros++) {
		while (contagem[cilindros] > 0) {
			for (int i = 0; i < n; i++) {
				if (veiculos[i].cilindros == cilindros) {
					ordenados[posicao] = veiculos[i];
					posicao++;
				}
			}

			contagem[cilindros] = 0;

		}
	}

	for (int i = 0; i < n; i++) {
		veiculos[i] = ordenados[i];
	}

	free(contagem);
	free(ordenados);
 }
