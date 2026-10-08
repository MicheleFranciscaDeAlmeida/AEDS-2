#include <stdio.h>

int main() {
    double A, B, C;
    double areaTriangulo, areaCirculo, areaTrapezio, areaQuadrado, areaRetangulo;
    const double pi = 3.14159;

    // Leitura dos valores A, B e C
    scanf("%lf %lf %lf", &A, &B, &C);

    // Cálculo das áreas
    areaTriangulo = (A * C) / 2.0;
    areaCirculo = pi * C * C;
    areaTrapezio = ((A + B) * C) / 2.0;
    areaQuadrado = B * B;
    areaRetangulo = A * B;

    // Impressão dos resultados com três casas decimais
    printf("TRIANGULO: %.3lf\n", areaTriangulo);
    printf("CIRCULO: %.3lf\n", areaCirculo);
    printf("TRAPEZIO: %.3lf\n", areaTrapezio);
    printf("QUADRADO: %.3lf\n", areaQuadrado);
    printf("RETANGULO: %.3lf\n", areaRetangulo);

    return 0;
}