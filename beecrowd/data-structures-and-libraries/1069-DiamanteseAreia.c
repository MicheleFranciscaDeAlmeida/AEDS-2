#include <stdio.h>
#include <string.h>
#define MAX 1001


int main() {
	int n;
	scanf("%d", &n);
	
	// Consome o caractere de uma nova linha aṕos o número
	getchar();
        
        while(n--) {
	char str[MAX]; 
	fgets(str, MAX, stdin);
	
	//Remove o '\n' do final se existir
	int len = strlen(str);
	
	if(len > 0 && str[len - 1] == '\n') {
		str[len - 1] = '\0';
		len--;
	}

	
	// Usa uma pilha para contar os diamantes
	char stack[MAX];
	int top = -1;
	int diamonds = 0;

	for(int i = 0; i < len; i++) {
	if(str[i] == '<') {
		
	// Empilha o '<'
	top++;
	stack[top] = '<'; 
	} else if (str[i] == '>') {
	
	//Se tem um '<' no topo da pilha, forma um diamante
	if (top >= 0 && stack[top] == '<') {
		// Desempilha
	top--; 
	diamonds++; 
	
	}
	
	// Se não tem '<', ignora o '>' (areia ou sem par)
	
	}
	}
	
	printf("%d\n", diamonds);
	
	}
	
	return 0; 
}
