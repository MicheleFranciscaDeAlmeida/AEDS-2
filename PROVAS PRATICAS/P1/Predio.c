/* Questão1 em C - Localizacao dos predios
em comemoracao aos 40 anos do curso, diversos egressos retornarão a universidade . Como os predios 
do campus possuem numeros de identificacao que nao seguem uma ordem relacionada aos seus nomes, sera
criada uma relacao p/ facilitar sua localizacao.
a relacao deve ser organizada em ordem alfabetica pelo nome do predio. Em seguida, os egressos poderao
consultar o numero de determinados predios. 
entrada
a entrada contem diversos casos de teste e termina com o fim do arquivo EOF.
cada caso comeca com um inteiro N (1 <= N <= 1000), seguido de N linhas no formato: 
numero nome. 
o numero é um inteiro positivo. o nome é uma string de até 30 caracteres, contem apenas letras, nao possui
espacos e nao se repete.
em seguida um inteiro P (1 <= P <= N),indica a quantidade de consultas, seguido de P nomes de predios. Todo 
predio consultado estara cadastrado.
saida
para cada caso imprima primeiro todos os predios em ordem alfabetica pelo nome, no formato nome numero. 
em seguida imprima P consultas, na ordem em que foram realizadas, utilizando o mesmo formato, nao imprimir linhas em branco
ou separadores.
ex
entrada
5
34 Biblioteca
7 Laboratorios
21 Teatro
3 Reitoria
18 ComplexoEsportivo
3
Teatro
Biblioteca
Reitoria

saida 
Biblioteca 34
ComplexoEsportivo 18
Laboratorios 7
Reitoria 3
Teatro 21
Teatro 21
Biblioteca 34
Reitoria 3 
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int numero;
    char nome[31];
} Predio;

int main() {
    int N;

    while (scanf("%d", &N) != EOF) {
        Predio predios[N];

        // Leitura
        for (int i = 0; i < N; i++) {
            scanf("%d %30s", &predios[i].numero, predios[i].nome);
        }

        // Selection Sort por nome
        for (int i = 0; i < N - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < N; j++) {
                if (strcmp(predios[j].nome, predios[menor].nome) < 0) {
                    menor = j;
                }
            }

            // Troca
            if (menor != i) {
                Predio temp = predios[i];
                predios[i] = predios[menor];
                predios[menor] = temp;
            }
        }

        // Imprime os prédios ordenados
        for (int i = 0; i < N; i++) {
            printf("%s %d\n", predios[i].nome, predios[i].numero);
        }

        // Consultas
        int P;
        scanf("%d", &P);

        for (int i = 0; i < P; i++) {
            char consulta[31];
            scanf("%30s", consulta);

            for (int j = 0; j < N; j++) {
                if (strcmp(consulta, predios[j].nome) == 0) {
                    printf("%s %d\n", predios[j].nome, predios[j].numero);
                    break;
                }
            }
        }
    }

    return 0;
}