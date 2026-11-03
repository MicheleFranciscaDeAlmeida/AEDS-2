// Questão 16: Contar palavras de tamanho n

public class ContadorTamanho {
    private NoLetra raiz;

    // SOLUÇÃO DA QUESTÃO 16
    public int contarPalavras(String padrao) {
        if (padrao == null || padrao.isEmpty()) return 0;
        
        char letra = padrao.charAt(0);
        int tamanho = padrao.length();
        
        NoLetra no = raiz;
        while (no != null && no.letra != letra) {
            no = (letra < no.letra) ? no.esq : no.dir;
        }
        
        if (no == null) return 0;
        
        // Percorre a lista interna contando pelo tamanho
        return contarTamanhoLista(no.primeiro, tamanho);
    }

    private int contarTamanhoLista(CelulaPalavra i, int tamanho) {
        int count = 0;
        while (i != null) {
            if (i.palavra.length() == tamanho) {
                count++;
            }
            i = i.prox;
        }
        return count;
    }
}