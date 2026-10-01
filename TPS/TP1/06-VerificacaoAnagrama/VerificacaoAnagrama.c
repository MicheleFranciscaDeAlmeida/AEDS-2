#include <stdio.h>
#include <stdlib.h>

int VerificacaoAnagrama(char *str1, char *str2) {

    // Vetor para contar a ocorrência de cada caractere
    int alfabeto[256] = {0}; 
    int i = 0;
    int ehAnagrama = 1;

    // Conta os caracteres da primeira string
    for (i = 0; str1[i] != '\0'; i++) {
        if (str1[i] >= 'A' && str1[i] <= 'Z') {
            str1[i] += 32; 
        }
        alfabeto[(unsigned char)str1[i]]++;
    }

    // Diminui as ocorrências usando a segunda string
    for (i = 0; str2[i] != '\0'; i++) {

        // Converte letras maiúsculas para minúsculas
        if (str2[i] >= 'A' && str2[i] <= 'Z') {
            str2[i] += 32; 
        }
        alfabeto[(unsigned char)str2[i]]--;
    }

    // Verifica se todas as contagens ficaram zeradas, indicando que são anagramas
    for (i = 0; i < 256; i++) {
        if (alfabeto[i] != 0) {
            ehAnagrama = 0;
            break;
        }
    }
    return ehAnagrama;
}

int main() {
       char str1[100], str2[100];
       
    // Faz a leitura dos pares de strings até encontrar a palavra "FIM"
    while (scanf("%99s", str1) != EOF && 
    !(str1[0] == 'F' && 
      str1[1] == 'I' && 
      str1[2] == 'M' && 
      str1[3] == '\0')) {
 
        scanf("%99s",str2);

        // Verifica e imprime se as strings são anagramas ou não
        if (VerificacaoAnagrama(str1, str2)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
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