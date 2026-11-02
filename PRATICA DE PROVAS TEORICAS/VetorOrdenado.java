Questão 5 - Intercalação de dois vetores ordenados em ordem decrescente

public static int[] vetorOrdenado(int[] vetA, int[] vetB) {
    int m = vetA.length;
    int n = vetB.length;
    int[] vetC = new int[m + n];
    
    // Como vetA e vetB estão em ordem DECRESCENTE, começamos do final
    int i = m - 1;  // último índice de vetA
    int j = n - 1;  // último índice de vetB
    int k = 0;      // índice de vetC (preenchendo do início para CRESCENTE)
    
    // Intercala os dois vetores
    while (i >= 0 && j >= 0) {
        if (vetA[i] <= vetB[j]) {
            vetC[k++] = vetA[i--];
        } else {
            vetC[k++] = vetB[j--];
        }
    }
    
    // Copia elementos restantes de vetA
    while (i >= 0) {
        vetC[k++] = vetA[i--];
    }
    
    // Copia elementos restantes de vetB
    while (j >= 0) {
        vetC[k++] = vetB[j--];
    }
    
    return vetC;
}