// Questão 3 - Intercalar duas listas duplamente encadeadas de forma reversa

CelulaDupla intercalarReverso(CelulaDupla c1, CelulaDupla c2) {
    // c1 e c2 são células cabeça (sentinela)
    // Precisamos encontrar os últimos elementos de cada lista
    
    // Encontra o último elemento da lista 1
    CelulaDupla ultimo1 = c1.ant;
    
    // Encontra o último elemento da lista 2
    CelulaDupla ultimo2 = c2.ant;
    
    // Cria célula cabeça para a nova lista
    CelulaDupla novaCabeca = new CelulaDupla();
    novaCabeca.prox = null;
    novaCabeca.ant = null;
    
    CelulaDupla atualNova = novaCabeca;
    
    // Intercala enquanto houver elementos em ambas as listas
    while (ultimo1 != c1 && ultimo2 != c2) {
        // Adiciona elemento da lista 1
        CelulaDupla novoNo1 = new CelulaDupla();
        novoNo1.elemento = ultimo1.elemento;
        novoNo1.ant = atualNova;
        novoNo1.prox = null;
        atualNova.prox = novoNo1;
        atualNova = novoNo1;
        
        // Move para o anterior na lista 1
        ultimo1 = ultimo1.ant;
        
        // Adiciona elemento da lista 2
        CelulaDupla novoNo2 = new CelulaDupla();
        novoNo2.elemento = ultimo2.elemento;
        novoNo2.ant = atualNova;
        novoNo2.prox = null;
        atualNova = novoNo2;
        atualNova.prox = null;
        atualNova.ant.prox = atualNova;
        
        // Move para o anterior na lista 2
        ultimo2 = ultimo2.ant;
    }
    
    // Adiciona elementos restantes da lista 1
    while (ultimo1 != c1) {
        CelulaDupla novoNo = new CelulaDupla();
        novoNo.elemento = ultimo1.elemento;
        novoNo.ant = atualNova;
        novoNo.prox = null;
        atualNova.prox = novoNo;
        atualNova = novoNo;
        ultimo1 = ultimo1.ant;
    }
    
    // Adiciona elementos restantes da lista 2
    while (ultimo2 != c2) {
        CelulaDupla novoNo = new CelulaDupla();
        novoNo.elemento = ultimo2.elemento;
        novoNo.ant = atualNova;
        novoNo.prox = null;
        atualNova.prox = novoNo;
        atualNova = novoNo;
        ultimo2 = ultimo2.ant;
    }
    
    return novaCabeca;
}