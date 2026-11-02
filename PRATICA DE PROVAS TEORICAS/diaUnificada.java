// Questão 8 - Criar uma lista duplamente encadeada com os elementos da diagonal
// principal de uma matriz representada por uma lista encadeada
// de listas encadeadas

CelulaDupla diagUnificada() {
    // Cria célula cabeça para a lista duplamente encadeada resultante
    CelulaDupla cabeca = new CelulaDupla();
    cabeca.prox = null;
    cabeca.ant = null;
    
    CelulaDupla atualDupla = cabeca;
    
    // Navega pela diagonal principal
    CelulaMatriz atualMat = inicio;
    
    while (atualMat != null) {
        // Pega a lista desta célula da diagonal (pula a célula cabeça)
        Celula atualLista = atualMat.inicio.prox;
        
        // Adiciona todos os elementos desta lista à lista dupla
        while (atualLista != null) {
            CelulaDupla novoNo = new CelulaDupla();
            novoNo.elemento = atualLista.elemento;
            novoNo.ant = atualDupla;
            novoNo.prox = null;
            
            atualDupla.prox = novoNo;
            atualDupla = novoNo;
            
            atualLista = atualLista.prox;
        }
        
        // Move para a próxima célula da diagonal
        atualMat = atualMat.dir;
        if (atualMat != null) {
            atualMat = atualMat.inf;
        }
    }
    
    return cabeca;
}