#include <stdio.h>
#include <stdlib.h>

int meuStrlen(char *str) {
    int i = 0; 
    while (str[i] != '\0') {
        i++;
    }
    return i;

}

int max(int a, int b) {
    return (a > b) ? a : b;
}

int SubstringmaislongasemRepeticao(char *str) {
    int n = meuStrlen(str);
    
    if(n == 0) {
        return 0;
    }

    int ultima_ocorrencia[256];

    for (int i = 0; i < 256; i++) {
        ultima_ocorrencia[i] = -1;
    }

    int inicio = 0;
    int max_comprimento = 0;

    for (int fim = 0; fim < n; fim++) {
        if (ultima_ocorrencia[(unsigned char)str[fim]] >= inicio) {
            inicio = ultima_ocorrencia[(unsigned char)str[fim]] + 1;
        }

        ultima_ocorrencia[(unsigned char)str[fim]] = fim;

        max_comprimento = max(max_comprimento, fim - inicio + 1);
    }
    return max_comprimento;
    }

int main() {
    char str[100];
    while (scanf("%99s", str) != EOF && 
    !(str[0] == 'F' && 
    str[1] == 'I' && 
    str[2] == 'M' && 
    str[3] == '\0')) {
        printf("%d\n", SubstringmaislongasemRepeticao(str));
      }
    return 0;
    }
