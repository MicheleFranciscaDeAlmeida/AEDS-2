import java.util.Random;

class CustomArray {

    // Guarda o tamanho do array
    private int tam;

    // Array que será utilizado pelos algoritmos
    public int array[];

    // Retorna o tamanho do array
    public int getTam() {
        return this.tam;
    }

    // Construtor
    public CustomArray(int tam) {

        // Guarda o tamanho recebido
        this.tam = tam;

        // Cria o array com o tamanho informado
        this.array = new int[tam];

        // Preenche o array
        fillArray();
    }

    // Preenche o array com valores de 1 até tam
    public void fillArray() {

        for (int i = 0; i < tam; i++) {

            // Exemplo:
            // i = 0 → array[0] = 1
            // i = 1 → array[1] = 2
            // ...
            this.array[i] = i + 1;
        }
    }

    // Imprime todos os elementos do array
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
        // e vai andando para trás
        for (int i = tam - 1; i > 0; i--) {

            // Escolhe uma posição aleatória
            int j = random.nextInt(i + 1);

            // Guarda temporariamente o valor de array[i]
            int temp = array[i];

            // Coloca o valor de array[j] em array[i]
            array[i] = array[j];

            // Coloca o valor antigo de array[i] em array[j]
            array[j] = temp;
        }
    }

    // Faz um embaralhamento simples de pares de posições
    public void partialShuffle() {

        // Anda de duas em duas posições
        for (int i = 0; i < tam - 1; i += 2) {

            // Guarda o primeiro elemento
            int temp = array[i];

            // Troca os dois elementos
            array[i] = array[i + 1];

            // Coloca o primeiro elemento na segunda posição
            array[i + 1] = temp;
        }
    }
}