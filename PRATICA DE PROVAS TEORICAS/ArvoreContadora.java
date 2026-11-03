// Questão 11 - Arvore Contadora

class NoContador {
    public int elemento;
    public int quantidade;
    public NoContador esq, dir;

    public NoContador(int x) {
        elemento = x;
        quantidade = 1;
        esq = dir = null;
    }
}

public class ArvoreContadora {
    private NoContador raiz;

    public void inserir(int x) {
        raiz = inserirRec(x, raiz);
    }

    // SOLUÇÃO DA QUESTÃO 11
    private NoContador inserirRec(int x, NoContador i) {
        if (i == null) {
            i = new NoContador(x);
        } else if (x < i.elemento) {
            i.esq = inserirRec(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserirRec(x, i.dir);
        } else {
            i.quantidade++; // Elemento já existe, incrementa
        }
        return i;
    }
}