#include <stdio.h>

int main() {
	
	int countPositivos = 0;
	double somaPositivos = 0.0;
	double valor;
	
	for (int i = 0; i < 6; i++) {
		scanf("%lf", &valor);

		if (valor > 0) {
			countPositivos++;
			somaPositivos += valor;
		}
	}

	double media = somaPositivos / countPositivos;

	printf("%d valores positivos\n", countPositivos);
	printf("%.1lf\n", media);

	return 0;

}  
