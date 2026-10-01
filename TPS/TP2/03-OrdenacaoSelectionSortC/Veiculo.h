#ifndef VEICULO_H
#define VEICULO_H
#include "Data.h"
#include <stdbool.h>

// Constantes que definem os tamanhos maximos dos atributos do veiculo.
#define MAX_MARCA 50
#define MAX_MODELO 100
#define MAX_CATEGORIA 50
#define MAX_TRANSMISSAO 30
#define MAX_TRACAO 30
#define MAX_COMBUSTIVEIS 5
#define MAX_TAM_COMBUSTIVEL 30

// Estrutura que representa um veiculo.
typedef struct {
	int id;
	char marca[MAX_MARCA];
	char modelo[MAX_MODELO];
	int ano;
	char categoria[MAX_CATEGORIA];
	char combustivel[MAX_COMBUSTIVEIS][MAX_TAM_COMBUSTIVEL];
	int quantidadeCombustiveis;
	int cilindros;
	double cilindrada;
	char transmissao[MAX_TRANSMISSAO];
	char tracao[MAX_TRACAO];
	double consumoCidade;
	double consumoEstrada;
	double co2;
	bool turbo;
	Data dataRegistro;
} Veiculo;

// Converte uma linha do arquivo CSV em uma estrutura Veiculo.
Veiculo* parseVeiculo(char* s);

// Formata os dados de um Veiculo em uma string.
void formatVeiculo(Veiculo v, char* buffer);

// Ordena os veiculos em ordem crescente pelo atributo modelo.
void selectionSort(Veiculo* veiculos, int n);

#endif

