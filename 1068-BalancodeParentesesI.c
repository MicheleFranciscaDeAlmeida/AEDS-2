#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	char expr[1001];

	while(scanf("%s", expr) != EOF) {
		
		int contador = 0;
		int erro = 0;

		for(int i = 0; i < strlen(expr); i++) {

		if(expr[i] == '(') {
			contador++;
		} else if(expr[i] == ')') {
			contador--;
		}

		if(contador < 0) {
			erro = 1;
			break;
		}
		}

		if(contador == 0 && erro == 0) {
			printf("correct\n");
		} else {
			printf("incorrect\n"); 

	}
	}
		return 0;

}

