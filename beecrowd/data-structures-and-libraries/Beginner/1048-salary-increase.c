/* beecrowd | 1048
Aumento de Salário

Por Neilor Tonin, URI Brasil
Timelimit: 1

A empresa ABC resolveu conceder um aumento de salários a seus funcionários de acordo com a tabela abaixo:

Salário 	Percentual de Reajuste

0 - 400.00
400.01 - 800.00
800.01 - 1200.00
1200.01 - 2000.00
Acima de 2000.00
	

15%
12%
10%
7%
4%

Leia o salário do funcionário e calcule e mostre o novo salário, bem como o valor de reajuste ganho e o índice reajustado, em percentual.
Entrada

A entrada contém apenas um valor de ponto flutuante, com duas casas decimais.
Saída

Imprima 3 linhas na saída: o novo salário, o valor ganho de reajuste (ambos devem ser apresentados com 2 casas decimais) e o percentual de reajuste ganho, conforme exemplo abaixo.
Exemplo de Entrada 	Exemplo de Saída

400.00
	

Novo salario: 460.00
Reajuste ganho: 60.00
Em percentual: 15 %

800.01
	

Novo salario: 880.01
Reajuste ganho: 80.00
Em percentual: 10 %

2000.00
	

Novo salario: 2140.00
Reajuste ganho: 140.00
Em percentual: 7 %
*/

#include <stdio.h>

int main() {
    double salary, new_salary, increase;
    int percentage;

    scanf("%lf", &salary);

    if (salary <= 400.00) {
        percentage = 15;
    } else if (salary <= 800.00) {
        percentage = 12;
    } else if (salary <= 1200.00) {
        percentage = 10;
    } else if (salary <= 2000.00) {
        percentage = 7;
    } else {
        percentage = 4;
    }

    increase = salary * percentage / 100.0;
    new_salary = salary + increase;

    printf("Novo salario: %.2lf\n", new_salary);
    printf("Reajuste ganho: %.2lf\n", increase);
    printf("Em percentual: %d %%\n", percentage);

    return 0;
}