import java.util.*;

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

        for (int i = tam - 1; i > 0; i--) {

            // Escolhe uma posição aleatória
            int j = random.nextInt(i + 1);

            // Troca os elementos
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}