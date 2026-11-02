// Questão 6 - Remover a última coluna de uma matriz representada
// por uma lista encadeada de listas encadeadas

void removerUltimaColuna(Matriz *matriz) {
    if (matriz->colunas <= 0 || matriz->inicio == NULL) {
        return;
    }
    
    // Percorre todas as linhas e remove a última coluna
    for (int i = 0; i < matriz->linhas; i++) {
        // Encontra a célula da última coluna na linha i
        Celula *celula = matriz->inicio;
        
        // Navega até a linha i
        for (int r = 0; r < i; r++) {
            if (celula != NULL) {
                celula = celula->inf;
            }
        }
        
        // Navega até a última coluna
        for (int c = 0; c < matriz->colunas - 1; c++) {
            if (celula != NULL) {
                celula = celula->dir;
            }
        }
        
        // celula agora aponta para a última coluna da linha i
        if (celula != NULL) {
            // Ajusta ponteiro da esquerda
            if (celula->esq != NULL) {
                celula->esq->dir = NULL;
            }
            
            // Ajusta ponteiros superior e inferior
            if (celula->sup != NULL) {
                celula->sup->inf = NULL;
            }
            if (celula->inf != NULL) {
                celula->inf->sup = NULL;
            }
            
            // Libera a memória
            free(celula);
        }
    }
    
    // Atualiza o número de colunas
    matriz->colunas--;
}