#include <stdio.h>

int contChar(char str[]) {
    int i, cont = 0;
    for(i = 0; str[i] != '\0'; i++) {
        if(str[i] >= 'A' && str[i] <= 'Z') {
            cont++;
        }
    }
    return cont;
}

int main() {
    char pa[100];
    scanf(" %[^\n]", pa); 

    while(!(pa[0] == 'F' && pa[1] == 'I' && pa[2] == 'M' && pa[3] == '\0')) {
        printf("%d\n", contChar(pa));
        scanf(" %[^\n]", pa);

    }
    return 0;
}