#include <stdio.h>
#include <stdlib.h>

// Função auxiliar para calcular o comprimento da string
int meu_strlen(char s[]) {
    int i = 0;
    while (s[i] != '\0') {
        i++;
    }
    return i;
}

// Verifica se a String corresponde ao FIM da entrada.
int isFim(char s[]) {
    if (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && s[3] == '\0') {
        return 1;
    }
    return 0;
}

// Verifica se é uma vogal (auxiliar para limpeza do código)
int ehVogal(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
        c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
        return 1;
    }
    return 0;
}

// Verifica se é uma letra (auxiliar)
int ehLetra(char c) {
    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
        return 1;
    }
    return 0;
}

// Verifica se a String é composta SOMENTE por vogais.
int isVogais(char s[]) {
    int resultado = 1;
    int len = meu_strlen(s);
    int i = 0;

    // Se a string for vazia, não é composta por vogais
    if (len == 0) return 0;

    while (i < len && resultado == 1) {
        if (!ehVogal(s[i])) {
            resultado = 0; // Qualquer coisa que não seja vogal invalida
        }
        i++;
    }

    return resultado;
}

// Verifica se a String é composta SOMENTE por consoantes.
int isConsoantes(char s[]) {
    int resultado = 1;
    int len = meu_strlen(s);
    int i = 0;

    if (len == 0) return 0;

    while (i < len && resultado == 1) {
        // Se não for letra OU se for vogal, então não é somente consoante
        if (!ehLetra(s[i]) || ehVogal(s[i])) {
            resultado = 0;
        }
        i++;
    }

    return resultado;
}

// Verifica se a String representa um número inteiro.
int isInteiro(char s[]) {
    int len = meu_strlen(s);
    if (len == 0) return 0;
    
    int resultado = 1;
    int inicio = 0;
    int i = 0;
    
    if (s[0] == '-') {
        inicio = 1;
        if (len == 1) return 0; // Só o sinal negativo
    }

    i = inicio;
    while (i < len && resultado == 1) {
        if (!(s[i] >= '0' && s[i] <= '9')) {
            resultado = 0;
        }
        i++;
    }

    return resultado;
}

// Verifica se a String representa um número real.
int isReal(char s[]) {
    int len = meu_strlen(s);
    if (len == 0) return 0;

    int resultado = 1;
    int inicio = 0;
    int pontosE_virgulas = 0;
    int digitosEncontrados = 0;
    int i = 0;

    if (s[0] == '-') {
        inicio = 1;
        if (len == 1) return 0;
    }

    i = inicio;
    while (i < len && resultado == 1) {
        char c = s[i];

        if (c == '.' || c == ',') {
            pontosE_virgulas++;
        } else if (c >= '0' && c <= '9') {
            digitosEncontrados++;
        } else {
            resultado = 0; // Caractere inválido
        }
        
        i++;
    }

    // Regras para ser Real:
    // 1. No máximo um separador
    // 2. Deve ter pelo menos um dígito
    // 3. Não pode terminar com separador se não houver dígitos depois? 
    //    O enunciado Java original era permissivo, mas vamos seguir a lógica de "número válido".
    //    Geralmente "12." é considerado real em muitas implementações simples, mas ".12" também.
    //    O principal é ter dígitos e no máximo um separador.
    
    if (pontosE_virgulas > 1 || digitosEncontrados == 0) {
        resultado = 0;
    }

    return resultado;
}

int main() {
    char s[1000]; 
    int lerMais = 1;

    if (fgets(s, sizeof(s), stdin) != NULL) {
        int len = meu_strlen(s);
        if (len > 0 && s[len - 1] == '\n') s[len - 1] = '\0';

        while (lerMais && !isFim(s)) {
            printf("%s ", isVogais(s) ? "SIM" : "NAO");
            printf("%s ", isConsoantes(s) ? "SIM" : "NAO");
            printf("%s ", isInteiro(s) ? "SIM" : "NAO");
            printf("%s\n", isReal(s) ? "SIM" : "NAO");

            if (fgets(s, sizeof(s), stdin) != NULL) {
                len = meu_strlen(s);
                if (len > 0 && s[len - 1] == '\n') s[len - 1] = '\0';
            } else {
                lerMais = 0;
            }
        }
    }

    return 0;
}