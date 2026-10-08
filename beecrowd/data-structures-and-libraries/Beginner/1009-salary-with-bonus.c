#include <stdio.h>

int main() {
    char nome[50];
    double salarioFixo, totalVendas, totalReceber;

    // Leitura do nome do vendedor
    scanf("%s", nome);
    // Leitura do salário fixo e total de vendas
    scanf("%lf %lf", &salarioFixo, &totalVendas);

    // Cálculo do total a receber
    totalReceber = salarioFixo + (totalVendas * 0.15);

    // Impressão do resultado com duas casas decimais
    printf("TOTAL = R$ %.2lf\n", totalReceber);

    return 0;
}