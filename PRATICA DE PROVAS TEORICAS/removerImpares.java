// Questão 7 - Remover todos os números ímpares de uma matriz representada
// por uma lista encadeada de listas encadeadas

public void removerImpares() {
    CelulaMat atualMat = inicio;
    
    while (atualMat != null) {
        // Remove ímpares da lista desta célula da matriz
        Celula anterior = atualMat.primeiro;
        Celula corrente = anterior.prox;
        
        while (corrente != null) {
            if (corrente.numero % 2 != 0) {
                // É ímpar, remove
                anterior.prox = corrente.prox;
                
                // Se for o último, atualiza
                if (corrente == atualMat.ultimo) {
                    atualMat.ultimo = anterior;
                }
                
                // Avança sem mudar anterior
                corrente = anterior.prox;
            } else {
                // É par, mantém
                anterior = corrente;
                corrente = corrente.prox;
            }
        }
        
        atualMat = atualMat.inf;
    }
}