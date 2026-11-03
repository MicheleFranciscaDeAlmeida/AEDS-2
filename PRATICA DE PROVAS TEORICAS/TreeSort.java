// Questão 12 - Ordenação por Árvore Binária de Busca (Tree Sort)

public class TreeSort {
    
    private class No {
        int elemento;
        No esq, dir;
        No(int x) { elemento = x; }
    }

    private No raiz;
    private int pos; // Índice para preencher o array

    public void ordenar(int[] array) {
        raiz = null;
        // 1. Inserir todos os elementos na BST
        for (int x : array) {
            raiz = inserir(raiz, x);
        }
        
        // 2. Caminhamento Central para recuperar ordenado
        pos = 0;
        caminharCentral(raiz, array);
    }

    private No inserir(No i, int x) {
        if (i == null) return new No(x);
        if (x < i.elemento) i.esq = inserir(i.esq, x);
        else i.dir = inserir(i.dir, x); // Repetidos vão para a direita
        return i;
    }

    // SOLUÇÃO DA QUESTÃO 12 (Parte do caminhamento)
    private void caminharCentral(No i, int[] array) {
        if (i != null) {
            caminharCentral(i.esq, array);
            array[pos++] = i.elemento;
            caminharCentral(i.dir, array);
        }
    }
}