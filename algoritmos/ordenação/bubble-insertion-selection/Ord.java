import java.util.Scanner;

public class Ord {

    // Contador de comparações entre elementos
    static int comparacoes;

    // Contador de movimentações
    static int movimentacoes;


    // =========================================================
    // SELECTION SORT
    // =========================================================
    public static void selectionSort(CustomArray array) {

        // Reinicia os contadores
        comparacoes = 0;
        movimentacoes = 0;

        // Pega o tamanho do array
        int tam = array.getTam();

        System.out.println("O array inicial é esse:");
        array.printArray();


        // Percorre o array
        //
        // A cada passagem, vamos procurar
        // o menor elemento da parte ainda não ordenada.
        for (int i = 0; i < tam - 1; i++) {

            // Inicialmente, consideramos que
            // o menor elemento está na posição i.
            int menor = i;


            // Procura o menor elemento
            // nas posições depois de i.
            for (int j = i + 1; j < tam; j++) {

                // Fizemos uma comparação
                comparacoes++;

                // Se encontrou um elemento menor,
                // atualiza a posição do menor.
                if (array.array[j] < array.array[menor]) {

                    menor = j;
                }
            }


            // Depois de encontrar o menor elemento,
            // fazemos a troca com a posição i.
            if (menor != i) {

                int temp = array.array[i];

                array.array[i] = array.array[menor];

                array.array[menor] = temp;

                // Uma troca possui três movimentações
                movimentacoes += 3;
            }
        }


        System.out.println("Após Selection Sort:");
        array.printArray();

        System.out.println(
                "Foram feitas " + comparacoes +
                " comparacoes e " +
                movimentacoes +
                " movimentacoes."
        );
    }


    // =========================================================
    // BUBBLE SORT
    // =========================================================
    public static void bubbleSort(CustomArray array) {

        // Reinicia os contadores
        comparacoes = 0;
        movimentacoes = 0;

        int tam = array.getTam();

        System.out.println("O array inicial é esse:");
        array.printArray();


        // Cada passagem coloca um dos maiores elementos
        // na posição correta, no final do array.
        for (int i = 0; i < tam - 1; i++) {

            // Compara elementos vizinhos
            //
            // A parte final já está ordenada,
            // por isso usamos tam - 1 - i.
            for (int j = 0; j < tam - 1 - i; j++) {

                // Comparamos dois elementos vizinhos
                comparacoes++;

                // Se o elemento da esquerda for maior,
                // os dois estão fora de ordem.
                if (array.array[j] > array.array[j + 1]) {

                    // Guarda temporariamente o elemento da esquerda
                    int temp = array.array[j];

                    // Coloca o elemento da direita na esquerda
                    array.array[j] = array.array[j + 1];

                    // Coloca o elemento antigo na direita
                    array.array[j + 1] = temp;

                    // Uma troca possui três movimentações
                    movimentacoes += 3;
                }
            }
        }


        System.out.println("Após Bubble Sort:");
        array.printArray();

        System.out.println(
                "Foram feitas " + comparacoes +
                " comparacoes e " +
                movimentacoes +
                " movimentacoes."
        );
    }


    // =========================================================
    // INSERTION SORT
    // =========================================================
    public static void insertionSort(CustomArray array) {

        // Reinicia os contadores
        comparacoes = 0;
        movimentacoes = 0;

        int tam = array.getTam();

        System.out.println("O array inicial é esse:");
        array.printArray();


        // Começamos no índice 1,
        // porque consideramos o primeiro elemento
        // como a parte inicialmente ordenada.
        for (int i = 1; i < tam; i++) {

            // Guarda o elemento que queremos inserir
            int tmp = array.array[i];

            // Começamos olhando o elemento anterior
            int j = i - 1;


            // Enquanto j estiver dentro do array
            // e o elemento anterior for maior que tmp,
            // deslocamos o elemento para a direita.
            while (j >= 0) {

                comparacoes++;

                if (array.array[j] > tmp) {

                    // Desloca o elemento uma posição para a direita
                    array.array[j + 1] = array.array[j];

                    movimentacoes++;

                    // Volta uma posição
                    j--;

                } else {

                    // Se o elemento já estiver
                    // na posição correta, paramos.
                    break;
                }
            }


            // Coloca tmp na posição correta
            array.array[j + 1] = tmp;

            movimentacoes++;
        }


        System.out.println("Após Insertion Sort:");
        array.printArray();

        System.out.println(
                "Foram feitas " + comparacoes +
                " comparacoes e " +
                movimentacoes +
                " movimentacoes."
        );
    }


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String args[]) {

        Scanner scanf = new Scanner(System.in);


        // Pede o tamanho do array
        System.out.print("Digite o tamanho do array que deseja: ");

        int tam = scanf.nextInt();


        // Cria um novo CustomArray
        CustomArray array = new CustomArray(tam);


        // Embaralha o array
        array.customShuffle();


        // Mostra as opções de ordenação
        System.out.println(
                "(1) Selection Sort\n" +
                "(2) Bubble Sort\n" +
                "(3) Insertion Sort\n" +
                "Digite qual deseja utilizar:"
        );


        // Lê a escolha do usuário
        int escolha = scanf.nextInt();


        // Escolhe qual algoritmo executar
        switch (escolha) {

            case 1:

                selectionSort(array);

                break;


            case 2:

                bubbleSort(array);

                break;


            case 3:

                insertionSort(array);

                break;


            default:

                System.out.println("Opção inválida.");

                break;
        }


        // Fecha o Scanner
        scanf.close();
    }
}