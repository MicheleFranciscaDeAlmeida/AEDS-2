#include "Data.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Data parseData(char* s) {
	// Inicializa a data com zeros para o caso de a string ser invalida ou vazia.
	Data d = {0, 0, 0};

	if(s == NULL || strlen(s) == 0) {
		return d;
	}

	// Faz a leitura da data no formato AAAA-MM-DD e separa ano, mes e dia.
	int ano, mes, dia;
	if (sscanf(s, "%d-%d-%d", &ano, &mes, &dia) == 3) {
		d.dia = dia;
		d.mes = mes;
		d.ano = ano;
	}
	
	return d;
      }
     
     
     void formatData(Data d, char* buffer) {
	     // Converte a data para o formato DD/MM/AAAA.
	     sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
     }




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

            // Armazena o combustivel no primeiro elemento do vetor de combustiveis.
            case 5:
                v->quantidadeCombustiveis = 1;
                strncpy(v->combustivel[0], start,
                        MAX_TAM_COMBUSTIVEL - 1);
                break;

            case 6:
                v->cilindros = atoi(start);
                break;

            case 7:
                replaceCommaDot(start);
                v->cilindrada = atof(start);
                break;

            case 8:
                strncpy(v->transmissao, start, MAX_TRANSMISSAO - 1);
                break;

            case 9:
                strncpy(v->tracao, start, MAX_TRACAO - 1);
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

    // Monta a representacao dos combustiveis em uma unica string.
    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {
        strcat(combStr, v.combustivel[i]);

        if (i < v.quantidadeCombustiveis - 1) {
            strcat(combStr, ", ");
        }
    }

    // Substitui ponto e virgula por virgula no caso de multiplos combustiveis.
    for (int i = 0; combStr[i] != '\0'; i++) {
        if (combStr[i] == ';') {
            combStr[i] = ',';
        }
    }

    // Converte a data para o formato de saida.
    char dataStr[15];
    formatData(v.dataRegistro, dataStr);

    // Formata todos os atributos do veiculo conforme o padrao de saida.
    sprintf(buffer,
        "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
        v.id, v.marca, v.modelo, v.ano, v.categoria, combStr,
        v.cilindros, v.cilindrada, v.transmissao, v.tracao,
        v.consumoCidade, v.consumoEstrada, v.co2,
        v.turbo ? "true" : "false", dataStr);
}

void countingSortDigito(Veiculo* veiculos, int n, int exp) {
    int contagem[10] = {0};
    Veiculo* ordenados = malloc(n * sizeof(Veiculo));

    // Conta quantos veiculos possuem cada digito.
    for (int i = 0; i < n; i++) {
        int digito = (veiculos[i].ano / exp) % 10;
        contagem[digito]++;
    }

    // Acumula as contagens para descobrir as posicoes.
    for (int i = 1; i < 10; i++) {
        contagem[i] += contagem[i - 1];
    }

    // Percorre de tras para frente para manter a estabilidade.
    for (int i = n - 1; i >= 0; i--) {
        int digito = (veiculos[i].ano / exp) % 10;
        ordenados[contagem[digito] - 1] = veiculos[i];
        contagem[digito]--;
    }

    // Copia o resultado de volta para o array original.
    for (int i = 0; i < n; i++) {
        veiculos[i] = ordenados[i];
    }

    free(ordenados);
}

void radixSort(Veiculo* veiculos, int n) {
    if (n <= 1) {
        return;
    }

    int maior = veiculos[0].ano;

    // Descobre o maior ano.
    for (int i = 1; i < n; i++) {
        if (veiculos[i].ano > maior) {
            maior = veiculos[i].ano;
        }
    }

    // exp = 1 unidade
    // exp = 10 dezena
    // exp = 100 centena
    // exp = 1000 milhar

    for (int exp = 1; maior / exp > 0; exp *= 10) {
        countingSortDigito(veiculos, n, exp);
    }
}

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


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "LeitorCsvFunctions.h"

#define CAMINHO_DATASET "/tmp/veiculos.csv"
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

    Veiculo* selecionados = malloc(n * sizeof(Veiculo));
    int quantidade = 0;

    int idBusca;
    

    // Faz a leitura dos IDs dos veiculos até que seja informado -1.
    while (scanf("%d", &idBusca) == 1) {
        if (idBusca == -1) break;

        bool encontrado = false;

        // Percorre a frota procurando o veiculo pelo ID.	
        for (int i = 0; i < n; i++) {
            if (frota[i].id == idBusca) {

              // Guarda o veiculo selecionado.
	      selecionados[quantidade] = frota[i];
	      quantidade++;

	      encontrado = true;
	      break;
              }
           } 

        if (!encontrado) {
            printf("Veiculo nao encontrado.\n");
             }
           }
       
       // Ordena os veiculos selecionados pelo ano.
       radixSort(selecionados, quantidade);

       // Imprime os veiculos ja ordenados.
       char buffer[BUFFER_SAIDA];

       for (int i = 0; i < quantidade; i++) {
	       formatVeiculo(selecionados[i], buffer);
	       printf("%s\n", buffer);
       }
    	

    // Libera a memoria.
    free(selecionados);
    free(frota);

    return 0;
}
