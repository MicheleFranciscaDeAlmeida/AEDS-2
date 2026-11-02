// Questão 1 - Inserir no fim de uma lista duplamente encadeada circular
// com celula cabeça (sentinela)

void inserirFim(int x) {
    // Cria nova célula com o elemento x
    CelulaDupla* nova = novaCelulaDupla(x);
    
    // Encontra a última célula (anterior à célula cabeça)
    CelulaDupla* ultima = primeiro->ant;
    
    // Conecta a nova célula entre a última e a célula cabeça
    nova->prox = primeiro;      // próxima da nova aponta para cabeça
    nova->ant = ultima;         // anterior da nova aponta para última
    
    // Atualiza os ponteiros da última célula e da célula cabeça
    ultima->prox = nova;        // próxima da última agora é a nova
    primeiro->ant = nova;       // anterior da cabeça agora é a nova
}