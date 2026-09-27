#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>
#include <stdbool.h>


/* DATA */

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

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


/* VEICULO */

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


// Troca virgula decimal por ponto.
void replaceCommaDot(char* s) {

    if (s == NULL) {
        return;
    }

    for (char* p = s; *p != '\0'; p++) {
        if (*p == ',') {
            *p = '.';
        }
    }
}


Veiculo* parseVeiculo(char* s) {

    if (s == NULL || strlen(s) == 0) return NULL;
    
    // Reserva memória para cópia da string e copia a linha recebida.
    char* linha = (char*)malloc(strlen(s) + 1);

    if (!linha) {
        return NULL;
    }

    strcpy(linha, s);

    // Reserva memória para armazenar o veiculo.
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
                strncpy(v->marca, start, MAX_MARCA-1);
                break;

            case 2:
                strncpy(v->modelo, start, MAX_MODELO-1);
                break;

            case 3:
                v->ano = atoi(start);
                break;

            case 4:
                strncpy(v->categoria, start, MAX_CATEGORIA-1);
                break;
            
            // Armazena o combustivel no primeiro elemento do vetor de combustiveis.
            case 5:
                v->quantidadeCombustiveis = 1;

                for (char *p = start; *p != '0'; p++)
                    if (*p == ';') *p = ',';

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
                strncpy(v->transmissao, start, MAX_TRANSMISSAO-1);
                break;

            case 9:
                strncpy(v->tracao, start, MAX_TRACAO-1);
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
    
    // Monta a representacao dos combustiveis em uma unica string.
    char combStr[200] = "";

    for (int i = 0; i < v.quantidadeCombustiveis; i++) {
        strcat(combStr, v.combustivel[i]);

        if (i < v.quantidadeCombustiveis - 1)
            strcat(combStr, ",");
    }
    
    // Converte a data para o formato de saida.
    char dataStr[15];

    formatData(v.dataRegistro, dataStr);

    // Formata todos os atributos do veiculo conforme o padrao de saida.
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
        dataStr
    );
}


/* LEITOR CSV */

#define CAPACIDADE_INICIAL 100
#define MAX_LINHA 1000

Veiculo* lerCsv(char* caminhoArquivo, int* n) {

    FILE* arquivo = fopen(caminhoArquivo, "r");

    if (!arquivo) {
        fprintf(stderr,
                "Erro: Nao foi possivel abrir o arquivo %s\n",
                caminhoArquivo);
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

        if (len > 0 && linha[len - 1] == '\n')
            linha[len - 1] = '\0';

        if (primeiraLinha) {
            primeiraLinha = false;
            continue;
        }

        if (strlen(linha) == 0)
            continue;

        // Aumenta a capacidade do vetor quando todos os espaços disponiveis foram ocupados.
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

        // Converte a linha do CSV em um Veiculo.
        Veiculo* vPtr = parseVeiculo(linha);

        if (vPtr != NULL) {

            veiculos[count] = *vPtr;

            // Libera o malloc interno do parseVeiculo.
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

        if (temp)
            veiculos = temp;
    }

    *n = count;

    return veiculos;
}


/* LISTA FLEXIVEL */

typedef struct Celula {
    Veiculo elemento;
    struct Celula *prox;
} Celula;


typedef struct {
    Celula *primeiro;
    Celula *ultimo;
    int tamanho;
} Lista;


void inicializarLista(Lista *lista) {

    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}


Celula *novaCelula(Veiculo veiculo) {

    Celula *celula =
        (Celula *) malloc(sizeof(Celula));

    celula->elemento = veiculo;
    celula->prox = NULL;

    return celula;
}


void inserirInicio(Lista *lista, Veiculo veiculo) {

    Celula *nova = novaCelula(veiculo);

    nova->prox = lista->primeiro;
    lista->primeiro = nova;

    if (lista->ultimo == NULL) {
        lista->ultimo = nova;
    }

    lista->tamanho++;
}


void inserirFim(Lista *lista, Veiculo veiculo) {

    Celula *nova = novaCelula(veiculo);

    if (lista->ultimo == NULL) {
        lista->primeiro = nova;
        lista->ultimo = nova;
    } else {
        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }

    lista->tamanho++;
}


void inserir(Lista *lista, Veiculo veiculo, int posicao) {

    if (posicao <= 0) {
        inserirInicio(lista, veiculo);
        return;
    }

    if (posicao >= lista->tamanho) {
        inserirFim(lista, veiculo);
        return;
    }

    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }

    Celula *nova = novaCelula(veiculo);

    nova->prox = anterior->prox;
    anterior->prox = nova;

    lista->tamanho++;
}


Veiculo removerInicio(Lista *lista) {

    Celula *tmp = lista->primeiro;
    Veiculo removido = tmp->elemento;

    lista->primeiro = tmp->prox;

    if (lista->primeiro == NULL) {
        lista->ultimo = NULL;
    }

    free(tmp);

    lista->tamanho--;

    return removido;
}


Veiculo removerFim(Lista *lista) {

    if (lista->tamanho == 1) {
        return removerInicio(lista);
    }

    Celula *anterior = lista->primeiro;

    while (anterior->prox != lista->ultimo) {
        anterior = anterior->prox;
    }

    Veiculo removido = lista->ultimo->elemento;

    free(lista->ultimo);

    lista->ultimo = anterior;
    lista->ultimo->prox = NULL;

    lista->tamanho--;

    return removido;
}


Veiculo remover(Lista *lista, int posicao) {

    if (posicao <= 0) {
        return removerInicio(lista);
    }

    if (posicao >= lista->tamanho - 1) {
        return removerFim(lista);
    }

    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++) {
        anterior = anterior->prox;
    }

    Celula *tmp = anterior->prox;
    Veiculo removido = tmp->elemento;

    anterior->prox = tmp->prox;

    free(tmp);

    lista->tamanho--;

    return removido;
}


void mostrarLista(Lista *lista) {

    Celula *atual = lista->primeiro;
    char buffer[500];

    while (atual != NULL) {

        formatVeiculo(atual->elemento, buffer);

        printf("%s\n", buffer);

        atual = atual->prox;
    }
}


void liberarLista(Lista *lista) {

    Celula *atual = lista->primeiro;

    while (atual != NULL) {

        Celula *tmp = atual;

        atual = atual->prox;

        free(tmp);
    }

    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}


/* FUNÇÕES DO MAIN */

Veiculo *buscarVeiculo(Veiculo *frota, int n, int id) {

    for (int i = 0; i < n; i++) {

        if (frota[i].id == id) {
            return &frota[i];
        }
    }

    return NULL;
}


void imprimirRemovido(Veiculo veiculo) {

    printf("(R)%s %s\n",
           veiculo.marca,
           veiculo.modelo);
}


/* LOCALIZAÇÃO DO DATASET */

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


/* MAIN */

int main() {

    int n = 0;

    char* caminhoDataset = encontrarDataset();

    if (strlen(caminhoDataset) == 0) {

        printf(
            "Erro: Nao foi possivel abrir o arquivo veiculos.csv\n"
        );

        return 1;
    }

    Veiculo *frota = lerCsv(caminhoDataset, &n);

    if (!frota || n == 0) {

        printf("Nenhum veiculo carregado.\n");

        return 1;
    }

    Lista lista;

    inicializarLista(&lista);

    int id;

    // IDs iniciais.
    while (scanf("%d", &id) == 1) {

        if (id == -1) {
            break;
        }

        Veiculo *veiculo =
            buscarVeiculo(frota, n, id);

        if (veiculo != NULL) {
            inserirFim(&lista, *veiculo);
        }
    }

    int quantidadeComandos;

    scanf("%d", &quantidadeComandos);

    for (int i = 0; i < quantidadeComandos; i++) {

        char comando[3];

        scanf("%s", comando);

        if (strcmp(comando, "II") == 0) {

            scanf("%d", &id);

            Veiculo *veiculo =
                buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserirInicio(&lista, *veiculo);
            }

        } else if (strcmp(comando, "I*") == 0) {

            int posicao;

            scanf("%d %d",
                  &posicao,
                  &id);

            Veiculo *veiculo =
                buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserir(&lista,
                        *veiculo,
                        posicao);
            }

        } else if (strcmp(comando, "IF") == 0) {

            scanf("%d", &id);

            Veiculo *veiculo =
                buscarVeiculo(frota, n, id);

            if (veiculo != NULL) {
                inserirFim(&lista, *veiculo);
            }

        } else if (strcmp(comando, "RI") == 0) {

            Veiculo removido =
                removerInicio(&lista);

            imprimirRemovido(removido);

        } else if (strcmp(comando, "R*") == 0) {

            int posicao;

            scanf("%d", &posicao);

            Veiculo removido =
                remover(&lista, posicao);

            imprimirRemovido(removido);

        } else if (strcmp(comando, "RF") == 0) {

            Veiculo removido =
                removerFim(&lista);

            imprimirRemovido(removido);
        }
    }

    mostrarLista(&lista);

    liberarLista(&lista);

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