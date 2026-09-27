#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>

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

Data parseData(char* s);
void formatData(Data d, char* buffer);
Veiculo* parseVeiculo(char* s);
void formatVeiculo(Veiculo v, char* buffer);
Veiculo* lerCsv(char* caminhoArquivo, int* n);

/* Converte uma string de data para a estrutura Data. */
Data parseData(char* s) {

    Data d = {0, 0, 0};

    if (s == NULL || strlen(s) == 0) {
        return d;
    }

    int ano, mes, dia;

    if (sscanf(s, "%d-%d-%d", &ano, &mes, &dia) == 3) {
        d.dia = dia;
        d.mes = mes;
        d.ano = ano;
    }

    return d;
}


/* Converte uma Data para o formato DD/MM/AAAA. */
void formatData(Data d, char* buffer) {
    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}


/* Substitui virgulas por pontos para permitir a conversao de valores decimais. */
void replaceCommaDot(char* str) {

    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == ',') {
            str[i] = '.';
        }
    }
}


/* Converte uma linha do CSV em uma estrutura Veiculo. */
Veiculo* parseVeiculo(char* s) {

    if (s == NULL || strlen(s) == 0) {
        return NULL;
    }

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

        while (*start == ' ') {
            start++;
        }

        char* end = start + strlen(start) - 1;

        while (end > start && *end == ' ') {
            *end = '\0';
            end--;
        }

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


/* Formata os dados do veiculo em uma unica string. */
void formatVeiculo(Veiculo v, char* buffer) {

    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {

        strcat(combStr, v.combustivel[i]);

        if (i < v.quantidadeCombustiveis - 1) {
            strcat(combStr, ", ");
        }
    }

    char dataStr[15];

    formatData(v.dataRegistro, dataStr);

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


#define MAX_LINHA 500
#define CAPACIDADE_INICIAL 1000


/* Le o arquivo CSV e carrega os veiculos em memoria. */
Veiculo* lerCsv(char* caminhoArquivo, int* n) {

    FILE* arquivo = fopen(caminhoArquivo, "r");

    if (!arquivo) {
        fprintf(
            stderr,
            "Erro: Nao foi possivel abrir o arquivo %s\n",
            caminhoArquivo
        );

        *n = 0;

        return NULL;
    }

    int capacidade = CAPACIDADE_INICIAL;

    Veiculo* veiculos =
        (Veiculo*)malloc(capacidade * sizeof(Veiculo));

    if (!veiculos) {
        fclose(arquivo);
        *n = 0;
        return NULL;
    }

    char linha[MAX_LINHA];
    int count = 0;
    bool primeiraLinha = true;

    while (fgets(linha, MAX_LINHA, arquivo) != NULL) {

        size_t len = strlen(linha);

        if (len > 0 && linha[len - 1] == '\n') {
            linha[len - 1] = '\0';
        }

        if (primeiraLinha) {
            primeiraLinha = false;
            continue;
        }

        if (strlen(linha) == 0) {
            continue;
        }

        if (count >= capacidade) {

            capacidade *= 2;

            Veiculo* temp =
                (Veiculo*)realloc(
                    veiculos,
                    capacidade * sizeof(Veiculo)
                );

            if (!temp) {
                free(veiculos);
                fclose(arquivo);
                *n = 0;
                return NULL;
            }

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

        Veiculo* temp =
            (Veiculo*)realloc(
                veiculos,
                count * sizeof(Veiculo)
            );

        if (temp) {
            veiculos = temp;
        }
    }

    *n = count;

    return veiculos;
}


/*
 * Tenta encontrar o arquivo veiculos.csv nos caminhos usados
 * durante os testes, pois o caminho pode mudar dependendo
 * de onde o programa esta sendo executado.
 */
char* encontrarDataset() {

    static char caminho[200];

    const char* caminhos[] = {
        "../01-ModelagemJava/veiculos.csv",
        "01-ModelagemJava/veiculos.csv",
        "veiculos.csv",
        "../veiculos.csv",
        "../../01-ModelagemJava/veiculos.csv",
        "../../veiculos.csv",
        "/tmp/veiculos.csv"
    };

    int quantidade =
        sizeof(caminhos) / sizeof(caminhos[0]);

    for (int i = 0; i < quantidade; i++) {

        FILE* arquivo = fopen(caminhos[i], "r");

        if (arquivo != NULL) {

            fclose(arquivo);

            strcpy(caminho, caminhos[i]);

            return caminho;
        }
    }

    strcpy(caminho, "");

    return caminho;
}


#define TAMANHO_FILA 5
#define BUFFER_SAIDA 500


typedef struct {

    Veiculo array[TAMANHO_FILA];

    int inicio;

    int tamanho;

} Fila;


/* Inicializa a fila. */
void inicializar(Fila *fila) {

    fila->inicio = 0;

    fila->tamanho = 0;
}


/* Remove o primeiro elemento da fila. */
Veiculo remover(Fila *fila) {

    Veiculo removido =
        fila->array[fila->inicio];

    fila->inicio =
        (fila->inicio + 1) % TAMANHO_FILA;

    fila->tamanho--;

    return removido;
}


/* Insere no final da fila circular. */
void inserir(Fila *fila, Veiculo veiculo) {

    int posicao =
        (fila->inicio + fila->tamanho)
        % TAMANHO_FILA;

    fila->array[posicao] = veiculo;

    fila->tamanho++;
}


/* Procura um veículo pelo ID. */
Veiculo* buscarVeiculo(
    Veiculo *frota,
    int n,
    int id
) {

    for (int i = 0; i < n; i++) {

        if (frota[i].id == id) {
            return &frota[i];
        }
    }

    return NULL;
}


/* Imprime um veículo removido. */
void imprimirRemovido(Veiculo veiculo) {

    printf(
        "(R)%s %s\n",
        veiculo.marca,
        veiculo.modelo
    );
}


int main() {

    int n = 0;

   /* Procura o arquivo CSV antes de carregar os veiculos. */
char* caminhoDataset = encontrarDataset();

if (strlen(caminhoDataset) == 0) {

    printf(
        "Erro: Nao foi possivel abrir o arquivo veiculos.csv\n"
    );

    return 1;
}

    /* Carrega o dataset. */
    Veiculo *frota =
        lerCsv(caminhoDataset, &n);

    if (!frota || n == 0) {

        printf(
            "Nenhum veiculo carregado.\n"
        );

        return 1;
    }

    Fila fila;

    inicializar(&fila);

    int id;

    /*
     * Lê os IDs iniciais.
     *
     * Como a fila possui capacidade 5,
     * quando estiver cheia, remove o primeiro
     * antes de inserir o próximo.
     */
    while (scanf("%d", &id) == 1) {

        if (id == -1) {
            break;
        }

        Veiculo *veiculo =
            buscarVeiculo(
                frota,
                n,
                id
            );

        if (veiculo != NULL) {

            if (fila.tamanho == TAMANHO_FILA) {

                Veiculo removido =
                    remover(&fila);

                imprimirRemovido(removido);
            }

            inserir(&fila, *veiculo);
        }
    }


    /* Quantidade de comandos. */
    int quantidadeComandos;

    scanf(
        "%d",
        &quantidadeComandos
    );

    char comando;


    for (
        int i = 0;
        i < quantidadeComandos;
        i++
    ) {

        scanf(
            " %c",
            &comando
        );

        if (comando == 'R') {

            Veiculo removido =
                remover(&fila);

            imprimirRemovido(removido);

        } else if (comando == 'I') {

            scanf(
                "%d",
                &id
            );

            Veiculo *veiculo =
                buscarVeiculo(
                    frota,
                    n,
                    id
                );

            if (veiculo != NULL) {

                if (
                    fila.tamanho ==
                    TAMANHO_FILA
                ) {

                    Veiculo removido =
                        remover(&fila);

                    imprimirRemovido(removido);
                }

                inserir(
                    &fila,
                    *veiculo
                );
            }
        }
    }


    /* Mostra os elementos restantes da fila. */
    char buffer[BUFFER_SAIDA];

    for (
        int i = 0;
        i < fila.tamanho;
        i++
    ) {

        int posicao =
            (fila.inicio + i)
            % TAMANHO_FILA;

        formatVeiculo(
            fila.array[posicao],
            buffer
        );

        printf(
            "%s\n",
            buffer
        );
    }


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