#include <stdio.h>

// Inverte os caracteres de uma String recebida como parâmetro.
void inverterString(char str[]) {
    char temp;
    int i = 0;

    // Percorre a String até encontrar o final da mesma.
    while (str[i] != '\0') {
        i++;
    }

    // Percorre metade da String realizando a troca dos caracteres de início e fim.
    for (int j = 0; j < i / 2; j++) {
        temp = str[j];
        str[j] = str[i - j - 1];
        str[i - j - 1] = temp;
    }
}

int main() {
    char str[100];
    int i;

    // Faz a leitura de cada linha da entrada até o fim da entrada (EOF).
    while (fgets(str, sizeof(str), stdin) != NULL) {

        i = 0;

        // Percorre a String até encontrar o final da mesma ou uma quebra de linha.
        while (str[i] != '\0' && str[i] != '\n') {
            i++;
        }

        // Substitui a quebra de linha pelo caractere de fim da String.
        str[i] = '\0';

        // Encerra o programa se a String for "FIM".
        if (str[0] == 'F' && str[1] == 'I' && str[2] == 'M') {
            break;
        }

        // Inverte a String e imprime o resultado.
        inverterString(str);
        printf("%s\n", str);
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