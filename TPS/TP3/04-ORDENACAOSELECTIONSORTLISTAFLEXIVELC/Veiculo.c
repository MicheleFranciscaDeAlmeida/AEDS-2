#include "Veiculo.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>

// Substitui virgulas por pontos para permitir a conversao de valores decimais.
void replaceCommaDot(char* str) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == ',') {
            str[i] = '.';
        }
    }
}

// Converte uma linha do CSV em uma estrutura Veiculo.
Veiculo* parseVeiculo(char* s) {

    if (s == NULL || strlen(s) == 0) {
        return NULL;
    }

    // Reserva memoria para copia da string.
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

    // Separa a linha do CSV em campos usando a virgula.
    char* token = strtok(linha, ",");
    int campo = 0;

    // Percorre os campos da linha.
    while (token != NULL && campo < 15) {

        // Remove espacos no inicio e no final do campo.
        char* start = token;

        while (*start == ' ') {
            start++;
        }

        char* end = start + strlen(start) - 1;

        while (end > start && *end == ' ') {
            *end = '\0';
            end--;
        }

        // Converte cada campo para o atributo correspondente.
        switch (campo) {

            case 0:
                v->id = atoi(start);
                break;

            case 1:
                strncpy(v->marca, start, MAX_MARCA - 1);
                break;

            case 2:
                strncpy(v->modelo, start, MAX_MODELO - 1);
                break;

            case 3:
                v->ano = atoi(start);
                break;

            case 4:
                strncpy(v->categoria, start, MAX_CATEGORIA - 1);
                break;

            case 5:
                v->quantidadeCombustiveis = 1;

                strncpy(
                    v->combustivel[0],
                    start,
                    MAX_TAM_COMBUSTIVEL - 1
                );

                // O CSV usa ';' para separar multiplos combustiveis.
                // A saida esperada utiliza ','.
                for (int i = 0; v->combustivel[0][i] != '\0'; i++) {
                    if (v->combustivel[0][i] == ';') {
                        v->combustivel[0][i] = ',';
                    }
                }

                break;

            case 6:
                v->cilindros = atoi(start);
                break;

            case 7:
                replaceCommaDot(start);
                v->cilindrada = atof(start);
                break;

            case 8:
                strncpy(
                    v->transmissao,
                    start,
                    MAX_TRANSMISSAO - 1
                );
                break;

            case 9:
                strncpy(
                    v->tracao,
                    start,
                    MAX_TRACAO - 1
                );
                break;

            case 10:
                replaceCommaDot(start);
                v->consumoCidade = atof(start);
                break;

            case 11:
                replaceCommaDot(start);
                v->consumoEstrada = atof(start);
                break;

            case 12:
                replaceCommaDot(start);
                v->co2 = atof(start);
                break;

            case 13:
                v->turbo = (strcmp(start, "true") == 0);
                break;

            case 14:
                v->dataRegistro = parseData(start);
                break;
        }

        campo++;
        token = strtok(NULL, ",");
    }

    free(linha);

    return v;
}

// Formata os dados do veiculo em uma unica string.
void formatVeiculo(Veiculo v, char* buffer) {

    // Monta a representacao dos combustiveis.
    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {

        strcat(combStr, v.combustivel[i]);

        if (i < v.quantidadeCombustiveis - 1) {
            strcat(combStr, ", ");
        }
    }

    // Converte a data para o formato de saida.
    char dataStr[15];

    formatData(v.dataRegistro, dataStr);

    // Formata todos os atributos do veiculo.
    sprintf(
        buffer,
        "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria,
        combStr,
        v.cilindros,
        v.cilindrada,
        v.transmissao,
        v.tracao,
        v.consumoCidade,
        v.consumoEstrada,
        v.co2,
        v.turbo ? "true" : "false",
        dataStr
    );
}

// Ordena os veiculos em ordem crescente pelo atributo modelo.
void selectionSort(Veiculo* veiculos, int n) {

    for (int i = 0; i < n - 1; i++) {

        int menor = i;

        for (int j = i + 1; j < n; j++) {

            if (strcasecmp(veiculos[j].modelo, veiculos[menor].modelo) < 0) {

                menor = j;
            }
        }

        if (menor != i) {

            Veiculo temp = veiculos[i];

            veiculos[i] = veiculos[menor];

            veiculos[menor] = temp;
        }
    }
}