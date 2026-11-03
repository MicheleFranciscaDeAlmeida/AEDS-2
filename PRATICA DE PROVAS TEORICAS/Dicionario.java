// Questão 13 - Dicionário com Árvore Binária de Busca e Listas Ligadas

class CelulaPalavra {
    String palavra;
    CelulaPalavra prox;
    
    CelulaPalavra(String p) {
        this.palavra = p;
        this.prox = null;
    }
}

class NoLetra {
    char letra;
    NoLetra esq, dir;
    CelulaPalavra primeiro, ultimo; // Lista de palavras desta letra
    
    NoLetra(char l) {
        this.letra = l;
        this.esq = null;
        this.dir = null;
        this.primeiro = null;
        this.ultimo = null;
    }
}

public class Dicionario {
    private NoLetra raiz;

    // SOLUÇÃO DA QUESTÃO 13
    public void inserir(String palavra) {
        if (palavra == null || palavra.isEmpty()) return;
        
        char letra = palavra.charAt(0);
        NoLetra no = raiz;
        NoLetra pai = null;

        // 1. Procurar ou criar o nó da letra na árvore
        while (no != null && no.letra != letra) {
            pai = no;
            no = (letra < no.letra) ? no.esq : no.dir;
        }

        if (no == null) {
            no = new NoLetra(letra);
            if (pai == null) raiz = no;
            else if (letra < pai.letra) pai.esq = no;
            else pai.dir = no;
        }

        // 2. Inserir na lista ordenada alfabeticamente
        CelulaPalavra nova = new CelulaPalavra(palavra);
        CelulaPalavra ant = null;
        CelulaPalavra i = no.primeiro;

        while (i != null && i.palavra.compareTo(palavra) < 0) {
            ant = i;
            i = i.prox;
        }

        nova.prox = i;
        if (ant == null) no.primeiro = nova; // Insere no início
        else ant.prox = nova;
        
        if (i == null) no.ultimo = nova; // Atualiza último se inseriu no fim
    }
}