#include <stdio.h>
#include <stdlib.h>

int SomaDigitos(int num) {
    int soma = 0; 

    // Trata números negativos convertendo-os para positivos
    if (num < 0) {
        num = -num;
    }
    
    // Soma os dígitos até o número chegar a zero
    while (num > 0) {
        // Pega o último dígito e adiciona à soma
        soma += num % 10;

        // Remove o último dígito através da divisão por 10
        num /= 10;
    }
    return soma;
    }

    int main() {
        int num;
        // Faz a leitura dos números até o fim da entrada
        while (scanf("%d", &num) != EOF) {

            // Imprime a soma dos dígitos de cada valor lido
            printf("%d\n", SomaDigitos(num));
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
