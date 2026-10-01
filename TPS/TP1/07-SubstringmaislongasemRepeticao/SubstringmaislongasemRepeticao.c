#include <stdio.h>
#include <stdlib.h>

int meuStrlen(char *str) {
    int i = 0;
    
    // Percorre a string até encontrar o FIM 
    while (str[i] != '\0') {
        i++;
    }
    return i;

}

// Retorna o maior entre os dois valores
int max(int a, int b) {
    return (a > b) ? a : b;
}

int SubstringmaislongasemRepeticao(char *str) {
    // Obtém o tamanho da string
    int n = meuStrlen(str);
    
    // Trata string vazia
    if(n == 0) {
        return 0;
    }

    // Guarda a última posição de cada caractere
    int ultima_ocorrencia[256];
    
    // Inicializa as posições como - 1
    for (int i = 0; i < 256; i++) {
        ultima_ocorrencia[i] = -1;
    }

    int inicio = 0;
    int max_comprimento = 0;
    
    // Percorre a string usando o fim da substring atual
    for (int fim = 0; fim < n; fim++) {
	// Se o caractere já apareceu na substring atual, move o ínicio
        if (ultima_ocorrencia[(unsigned char)str[fim]] >= inicio) {
            inicio = ultima_ocorrencia[(unsigned char)str[fim]] + 1;
        }
	// Atualiza a última posição do caractere
        ultima_ocorrencia[(unsigned char)str[fim]] = fim;
	
	// Atualiza o maior comprimento encontrado
        max_comprimento = max(max_comprimento, fim - inicio + 1);
    }
    return max_comprimento;
    }

int main() {
    char str[100];

    // Faz a leitura das strings até encontrar FIM
    while (scanf("%99s", str) != EOF && 
    !(str[0] == 'F' && 
    str[1] == 'I' && 
    str[2] == 'M' && 
    str[3] == '\0')) {
	
	// Imprime o maior comprimento sem repetição
        printf("%d\n", SubstringmaislongasemRepeticao(str));
     
    }
    
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
