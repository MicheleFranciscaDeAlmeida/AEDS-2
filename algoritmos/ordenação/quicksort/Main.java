public class Main {

    // Conta as comparações realizadas
    static int comparacoes = 0;

    // Conta as movimentações realizadas
    static int movimentacoes = 0;

    // Array que será utilizado pelo Quick Sort
    static CustomArray Classe;


    // =========================================================
    // TROCA DOIS ELEMENTOS
    // =========================================================
    static void swap(int i, int j) {

        // Guarda temporariamente o elemento da posição i
        int tmp = Classe.array[i];

        // Coloca o elemento de j em i
        Classe.array[i] = Classe.array[j];

        // Coloca o elemento antigo de i em j
        Classe.array[j] = tmp;

        // Uma troca possui três movimentações
        movimentacoes += 3;
    }


    // =========================================================
    // QUICK SORT
    // =========================================================
    static void quickSort() {

        // Primeira chamada:
        // começa na primeira posição
        // e termina na última posição.
        quickSort(0, Classe.getTam() - 1);
    }


    // =========================================================
    // QUICK SORT RECURSIVO
    // =========================================================
    static void quickSort(int esq, int dir) {

        // i começa pela esquerda
        int i = esq;

        // j começa pela direita
        int j = dir;


        // Escolhe o elemento do meio como pivô
        int pivo = Classe.array[(esq + dir) / 2];


        // Enquanto os índices não se cruzarem,
        // continuamos fazendo a partição.
        while (i <= j) {


            // -------------------------------------------------
            // Procura pela esquerda
            // -------------------------------------------------
            //
            // Enquanto o elemento for menor que o pivô,
            // ele já está no lado correto.
            //
            // Então avançamos i.
            while (Classe.array[i] < pivo) {

                comparacoes++;

                i++;
            }


            // Conta a comparação que fez o while parar
            comparacoes++;


            // -------------------------------------------------
            // Procura pela direita
            // -------------------------------------------------
            //
            // Enquanto o elemento for maior que o pivô,
            // ele já está no lado correto.
            //
            // Então diminuímos j.
            while (Classe.array[j] > pivo) {

                comparacoes++;

                j--;
            }


            // Conta a comparação que fez o while parar
            comparacoes++;


            // -------------------------------------------------
            // TROCA
            // -------------------------------------------------
            //
            // Se i e j ainda não se cruzaram,
            // os elementos encontrados estão no lado errado.
            if (i <= j) {

                swap(i, j);

                // Depois da troca, avançamos os dois índices
                i++;
                j--;
            }
        }


        // =====================================================
        // RECURSÃO - PARTE ESQUERDA
        // =====================================================
        //
        // Se ainda existe uma parte para ordenar
        // à esquerda, fazemos uma nova chamada.
        if (esq < j) {

            quickSort(esq, j);
        }


        // =====================================================
        // RECURSÃO - PARTE DIREITA
        // =====================================================
        //
        // Se ainda existe uma parte para ordenar
        // à direita, fazemos uma nova chamada.
        if (i < dir) {

            quickSort(i, dir);
        }
    }


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String args[]) {

        // Cria um array com 100 elementos
        Classe = new CustomArray(100);


        // Embaralha o array
        Classe.customShuffle();


        // Mostra o tamanho correto
        System.out.println(
                "Tamanho: " + Classe.getTam()
        );


        // Executa o Quick Sort
        quickSort();


        // Mostra o array ordenado
        Classe.printArray();


        // Mostra as estatísticas
        System.out.println(
                "Comparacoes: " + comparacoes +
                " | Movimentacoes: " + movimentacoes
        );
    }
}