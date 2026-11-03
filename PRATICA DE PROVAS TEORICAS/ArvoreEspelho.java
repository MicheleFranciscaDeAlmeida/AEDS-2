// Questão 10 - Implementar um método que verifique se duas árvores binárias
// são espelhos uma da outra.

class No {
    int elemento;
    No esq, dir;
    
    No(int elem) {
        this.elemento = elem;
        this.esq = null;
        this.dir = null;
    }
}

public class ArvoreEspelho {
    
    // Método público inicial
    public boolean isEspelho(No a, No b) {
        return isEspelhoRec(a, b);
    }

    // SOLUÇÃO DA QUESTÃO 10
    private boolean isEspelhoRec(No a, No b) {
        if (a == null && b == null) return true; // Ambas vazias
        if (a == null || b == null) return false; // Apenas uma vazia
        if (a.elemento != b.elemento) return false; // Valores diferentes
        
        // Recursão cruzada: esquerda de A com direita de B e vice-versa
        return isEspelhoRec(a.esq, b.dir) && isEspelhoRec(a.dir, b.esq);
    }
}