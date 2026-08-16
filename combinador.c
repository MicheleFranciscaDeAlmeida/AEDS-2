#include <stdio.h>
#include <string.h>

int main() {
    char s1[100], s2[100];

    while(scanf("%s %s", s1, s2) != EOF) {
        int l1 = strlen(s1);
        int l2 = strlen(s2);
        int maxl = l1 > l2 ? l1 : l2;
        char resultado [200];
        int ind = 0;

    for (int i = 0; i < maxl; i++) {
        if(i < l1) {
            resultado [ind++] = s1[i];
        }
        if(i < l2) {
            resultado [ind++] = s2[i];
        }
    }
    resultado[ind] = '\0';
    printf("%s\n", resultado);
    }
    return 0; 
}