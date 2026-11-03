// Questão 15 - Contador de palavras com Árvore Binária de Busca e Listas Ligadas

public class ContadorPalavras {
    private NoLetra raiz; // Mesma estrutura da questão 13

    // SOLUÇÃO DA QUESTÃO 15
    public int contarPalavras(char primeiro, char ultimo) {
        NoLetra no = raiz;
        
        // Buscar o nó da árvore correspondente à primeira letra
        while (no != null && no.letra != primeiro) {
            no = (primeiro < no.letra) ? no.esq : no.dir;
        }
        
        if (no == null) return 0;
        
        // Chama recursão na lista/árvore interna dessa letra
        // Nota: O enunciado original menciona "No2" como árvore interna, 
        // mas na Q13 usamos lista. Se for lista, precisamos percorrer linearmente.
        // Assumindo a estrutura da Q13 (Lista):
        return contarNaLista(no.primeiro, ultimo);
    }

    private int contarNaLista(CelulaPalavra i, char ultimo) {
        int count = 0;
        while (i != null) {
            String p = i.palavra;
            if (!p.isEmpty() && p.charAt(p.length() - 1) == ultimo) {
                count++;
            }
            i = i.prox;
        }
        return count;
    }
    
    // SE a estrutura interna fosse uma ÁRVORE (No2) como sugerido no texto da resolução:
    /*
    private int contarEmArvore(No2 i, char ultimo) {
        if (i == null) return 0;
        int resp = 0;
        String p = i.palavra;
        if (!p.isEmpty() && p.charAt(p.length() - 1) == ultimo) resp = 1;
        return resp + contarEmArvore(i.esq, ultimo) + contarEmArvore(i.dir, ultimo);
    }
    */
}