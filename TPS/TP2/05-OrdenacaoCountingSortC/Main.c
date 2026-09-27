#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>
#include <stdbool.h>

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

#define MAX_MARCA 50
#define MAX_MODELO 100
#define MAX_CATEGORIA 50
#define MAX_TRANSMISSAO 30
#define MAX_TRACAO 30
#define MAX_COMBUSTIVEIS 5
#define MAX_TAM_COMBUSTIVEL 30

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
    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

// Substitui virgulas por pontos para permitir a conversao de valores decimais.
void replaceCommaDot(char* str) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == ',') str[i] = '.';
    }
}

// Converte uma linha do CSV em uma estrutura Veiculo.
Veiculo* parseVeiculo(char* s) {
    if (s == NULL || strlen(s) == 0) return NULL;
    
    char* linha = (char*)malloc(strlen(s) + 1);

    if (!linha) {
        return NULL;
    }

    strcpy(linha, s);

    Veiculo* v = (Veiculo*)malloc(sizeof(Veiculo));

    if (!v) {
        free(linha);
        return NULL;
    }
    
    memset(v, 0, sizeof(Veiculo));
    
    char* token = strtok(linha, ",");
    int campo = 0;
    
    while (token != NULL && campo < 15) {
        char* start = token;

        while (*start == ' ') start++;

        char* end = start + strlen(start) - 1;

        while (end > start && *end == ' ') { 
            *end = '\0';
            end--;
        }
        
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
            case 5:
                v->quantidadeCombustiveis = 1;
                strncpy(v->combustivel[0], start, MAX_TAM_COMBUSTIVEL - 1);
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

void formatVeiculo(Veiculo v, char* buffer) {
    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {
        strcat(combStr, v.combustivel[i]);

        if (i < v.quantidadeCombustiveis - 1) {
            strcat(combStr, ", ");
        }
    }

    for (int i = 0; combStr[i] != '\0'; i++) {
        if (combStr[i] == ';') {
            combStr[i] = ',';
        }
    }
    
    char dataStr[15];
    formatData(v.dataRegistro, dataStr);

    sprintf(buffer,
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
        dataStr);
}

// Ordena os veiculos pelo atributo cilindros usando Counting Sort.
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

        if (count >= capacidade) {
            capacidade *= 2;
            Veiculo* temp = (Veiculo*)realloc(veiculos, capacidade * sizeof(Veiculo));
            if (!temp) { free(veiculos); fclose(arquivo); *n = 0; return NULL; }
            veiculos = temp;
        }

        Veiculo* vPtr = parseVeiculo(linha);
        if (vPtr != NULL) {
            veiculos[count] = *vPtr;
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

#define CAMINHO_DATASET "/tmp/veiculos.csv"
#define BUFFER_SAIDA 500

int main() {
    int n = 0;

    Veiculo* frota = lerCsv(CAMINHO_DATASET, &n);

    if (!frota || n == 0) {
        printf("Nenhum veiculo carregado.\n");
        return 1;
    }

    Veiculo* selecionados = malloc(n * sizeof(Veiculo));
    int quantidade = 0;

    int idBusca;

    while (scanf("%d", &idBusca) == 1) {
        if (idBusca == -1) break;

        bool encontrado = false;

        for (int i = 0; i < n; i++) {
            if (frota[i].id == idBusca) {
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
       
    // Ordena os veiculos selecionados por quantidade de cilindros.
    countingSort(selecionados, quantidade);

    char buffer[BUFFER_SAIDA];

    for (int i = 0; i < quantidade; i++) {
        formatVeiculo(selecionados[i], buffer);
        printf("%s\n", buffer);
    }
    
    free(selecionados);
    free(frota);

    return 0;
}


/*
 * Uso de IA:
 * A ferramenta de Inteligência Artificial foi utilizada como apoio
 * para fundamentação, documentação e compreensão do enunciado,
 * auxiliando na análise da lógica e na revisão do código.
 *
 * A implementação foi desenvolvida a partir do meu próprio
 * raciocínio e entendimento do problema, com testes e validação
 * realizados por mim.
 */