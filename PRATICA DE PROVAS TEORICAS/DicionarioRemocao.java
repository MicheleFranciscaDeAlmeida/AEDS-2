// Questão 14 - Remoção de palavras do dicionário

public class DicionarioRemocao extends Dicionario {
    
    // SOLUÇÃO DA QUESTÃO 14
    public void remover(String palavra, NoLetra raiz) {
        if (palavra == null || palavra.isEmpty()) return;
        
        char letra = palavra.charAt(0);
        NoLetra no = raiz;
        
        // 1. Encontrar o nó da letra
        while (no != null && no.letra != letra) {
            no = (letra < no.letra) ? no.esq : no.dir;
        }
        
        if (no == null) return; // Letra não existe

        // 2. Remover da lista encadeada
        CelulaPalavra ant = null;
        CelulaPalavra i = no.primeiro;
        
        while (i != null && i.palavra.compareTo(palavra) < 0) {
            ant = i;
            i = i.prox;
        }

        // Verifica se encontrou a palavra exata
        if (i == null || !i.palavra.equals(palavra)) {
            return; 
        }

        // Ajuste dos ponteiros da lista
        if (ant == null) no.primeiro = i.prox; // Remove primeiro
        else ant.prox = i.prox;
        
        if (i == no.ultimo) no.ultimo = ant; // Atualiza último
    }
}