import java.util.Random;

class CustomArray {

    private int tam;
    public int array[];


    // Retorna o tamanho do array
    public int getTam() {

        return this.tam;
    }


    // Construtor
    public CustomArray(int tam) {

        this.tam = tam;
        this.array = new int[tam];

        // Preenche o array
        fillArray();
    }


    // Preenche o array com valores de 1 até tam
    public void fillArray() {

        for (int i = 0; i < tam; i++) {

            this.array[i] = i + 1;
        }
    }


    // Imprime o array
    public void printArray() {

        System.out.print("|");

        for (int i = 0; i < tam; i++) {

            System.out.print(array[i] + "|");
        }

        System.out.println();
    }


    // Embaralha o array
    public void customShuffle() {

        Random random = new Random();

        // Começa pela última posição
        // e vai até a segunda posição.
        for (int i = tam - 1; i > 0; i--) {

            // Escolhe uma posição aleatória
            int j = random.nextInt(i + 1);


            // Faz a troca
            int temp = array[i];

            array[i] = array[j];

            array[j] = temp;
        }
    }


    // Faz um embaralhamento parcial
    public void partialShuffle() {

        // Anda de duas em duas posições
        //
        // tam - 1 evita acessar array[i + 1]
        // quando o tamanho for ímpar.
        for (int i = 0; i < tam - 1; i += 2) {

            int temp = array[i];

            array[i] = array[i + 1];

            array[i + 1] = temp;
        }
    }
}