import java.io.*;

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    // Método público para inserir
    public void inserir(int x) {
        raiz = inserirRec(raiz, x);
    }

    // Método recursivo auxiliar para inserir
    private No inserirRec(No no, int x) {
        if (no == null) {
            return new No(x);
        }

        if (x < no.conteudo) {
            no.esq = inserirRec(no.esq, x);
        } else if (x > no.conteudo) {
            no.dir = inserirRec(no.dir, x);
        }
        // Se for igual, não insere duplicado (comportamento padrão de ABP simples)
        
        return no;
    }

    // Método público para pesquisar
    public boolean pesquisar(int x) {
        System.out.print("Pesquisando " + x + ": "); // Opcional, mas o exemplo não mostra isso, vamos seguir estritamente o output
        // O exemplo mostra: "50 70 60 S" na mesma linha da pesquisa? 
        // Olhando o pub.out:
        // P 60 -> 50 70 60 S
        // P 25 -> 50 30 20 N
        // Isso significa que o método pesquisar deve imprimir os nós visitados E o resultado (S/N).
        
        return pesquisarRec(raiz, x);
    }

    private boolean pesquisarRec(No no, int x) {
        if (no == null) {
            System.out.println("N");
            return false;
        }

        // Imprime o nó atual seguido de espaço
        System.out.print(no.conteudo + " ");

        if (x == no.conteudo) {
            System.out.println("S");
            return true;
        } else if (x < no.conteudo) {
            return pesquisarRec(no.esq, x);
        } else {
            return pesquisarRec(no.dir, x);
        }
    }

    // Caminhamento Pré-ordem (Raiz, Esq, Dir)
    public void caminharPre() {
        if (raiz == null) {
            System.out.println("V");
            return;
        }
        caminharPreRec(raiz);
        System.out.println(); // Nova linha ao final
    }

    private void caminharPreRec(No no) {
        if (no != null) {
            System.out.print(no.conteudo + " ");
            caminharPreRec(no.esq);
            caminharPreRec(no.dir);
        }
    }

    // Caminhamento Pós-ordem (Esq, Dir, Raiz)
    public void caminharPos() {
        if (raiz == null) {
            System.out.println("V");
            return;
        }
        caminharPosRec(raiz);
        System.out.println();
    }

    private void caminharPosRec(No no) {
        if (no != null) {
            caminharPosRec(no.esq);
            caminharPosRec(no.dir);
            System.out.print(no.conteudo + " ");
        }
    }

    // Caminhamento Em-ordem (Esq, Raiz, Dir)
    public void caminharEm() {
        if (raiz == null) {
            System.out.println("V");
            return;
        }
        caminharEmRec(raiz);
        System.out.println();
    }

    private void caminharEmRec(No no) {
        if (no != null) {
            caminharEmRec(no.esq);
            System.out.print(no.conteudo + " ");
            caminharEmRec(no.dir);
        }
    }
}
