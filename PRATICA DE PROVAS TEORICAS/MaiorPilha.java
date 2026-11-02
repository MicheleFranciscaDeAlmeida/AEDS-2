// Questão 4 - Maior pilha de uma lista encadeada

CelulaLista maiorPilha() {
    if (inicio == null) {
        return null;
    }
    
    CelulaLista maior = inicio;
    int maxTamanho = contaElementos(inicio.topo);
    
    CelulaLista atual = inicio.prox;
    
    while (atual != null) {
        int tamanhoAtual = contaElementos(atual.topo);
        if (tamanhoAtual > maxTamanho) {
            maxTamanho = tamanhoAtual;
            maior = atual;
        }
        atual = atual.prox;
    }
    
    return maior;
}

// Método auxiliar para contar elementos da pilha
private int contaElementos(CelulaPilha topo) {
    int count = 0;
    CelulaPilha atual = topo;
    while (atual != null) {
        count++;
        atual = atual.prox;
    }
    return count;
}