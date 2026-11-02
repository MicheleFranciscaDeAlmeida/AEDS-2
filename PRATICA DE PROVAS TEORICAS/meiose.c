// Questão 2 - Meiose de uma lista encadeada

void meiose() {
    // Se a lista estiver vazia, não há nada a fazer
    if (inicio == NULL) {
        return;
    }
    
    Celula* atual = inicio;
    
    // Percorre toda a lista
    while (atual != NULL) {
        // Calcula a metade do valor
        int metade = atual->elemento / 2;
        
        // Cria nova célula com a outra metade
        Celula* nova = (Celula*) malloc(sizeof(Celula));
        nova->elemento = atual->elemento - metade;  // Garante que a soma seja o valor original
        nova->prox = atual->prox;
        
        // Atualiza o valor da célula atual para a primeira metade
        atual->elemento = metade;
        
        // Insere a nova célula após a atual
        atual->prox = nova;
        
        // Avança para a próxima célula original (pula a nova inserida)
        atual = nova->prox;
    }
    
    // Atualiza o ponteiro fim para apontar para a última célula
    Celula* temp = inicio;
    while (temp->prox != NULL) {
        temp = temp->prox;
    }
    fim = temp;
}