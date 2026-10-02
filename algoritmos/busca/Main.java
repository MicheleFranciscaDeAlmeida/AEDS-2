import java.util.*;

public class Main {

    // Conta as comparações realizadas pelas pesquisas
    static int comparacoes;


    // =========================================================
    // PESQUISA BINÁRIA
    // =========================================================
    public static boolean pesquisaBinaria(
            CustomArray array,
            int pesquisa,
            int esq,
            int dir) {

        // Se o intervalo acabou, o elemento não foi encontrado
        if (esq > dir) {
            return false;
        }

        // Calcula o meio do intervalo
        int meio = (esq + dir) / 2;

        // Compara o valor pesquisado com o elemento do meio
        comparacoes++;

        // Elemento encontrado
        if (pesquisa == array.array[meio]) {
            return true;
        }

        // Se o elemento pesquisado for maior,
        // devemos procurar na metade direita
        if (pesquisa > array.array[meio]) {

            return pesquisaBinaria(
                    array,
                    pesquisa,
                    meio + 1,
                    dir
            );
        }

        // Caso contrário, procuramos na metade esquerda
        return pesquisaBinaria(
                array,
                pesquisa,
                esq,
                meio - 1
        );
    }


    // Método responsável pela interação com o usuário
    public static void pesquisaBinaria(
            CustomArray array,
            Scanner scanf) {

        comparacoes = 0;

        System.out.println(
                "Digite um número inteiro para pesquisar no array:"
        );

        int pesquisar = scanf.nextInt();

        System.out.println(
                "//----------------------------------------------//"
        );

        // O array começa ordenado
        System.out.println("Array ordenado:");

        array.printArray();

        boolean encontrado = pesquisaBinaria(
                array,
                pesquisar,
                0,
                array.getTam() - 1
        );

        if (encontrado) {
            System.out.println("Está presente no array");
        } else {
            System.out.println("Não está presente no array");
        }

        System.out.println(
                "Foram feitas " + comparacoes + " comparações"
        );


        System.out.println(
                "//----------------------------------------------//"
        );

        System.out.println(
                "A busca binária possui custo Θ(log n)."
        );

        System.out.println(
                "O array precisa estar ordenado para que "
                + "a busca binária funcione corretamente."
        );

        System.out.println(
                "//----------------------------------------------//"
        );


        // Agora embaralhamos o array
        array.customShuffle();

        System.out.println("Array desordenado:");

        array.printArray();

        comparacoes = 0;

        encontrado = pesquisaBinaria(
                array,
                pesquisar,
                0,
                array.getTam() - 1
        );

        if (encontrado) {
            System.out.println("Está presente no array");
        } else {
            System.out.println("Não está presente no array");
        }

        System.out.println(
                "Foram feitas " + comparacoes + " comparações"
        );

        System.out.println(
                "A busca binária não deve ser utilizada "
                + "diretamente em um array desordenado."
        );
    }


    // =========================================================
    // PESQUISA SEQUENCIAL
    // =========================================================
    public static boolean pesquisaSequencial(
            CustomArray array,
            int pesquisa,
            int tam) {

        // Percorre o array desde a primeira posição
        for (int i = 0; i < tam; i++) {

            // Compara o elemento atual com o pesquisado
            comparacoes++;

            if (array.array[i] == pesquisa) {

                // Encontrou o elemento
                return true;
            }
        }

        // Percorreu todo o array e não encontrou
        return false;
    }


    // Método responsável pela interação com o usuário
    public static void pesquisaSequencial(
            CustomArray array,
            Scanner scanf) {

        comparacoes = 0;

        System.out.println(
                "Digite um número inteiro para pesquisar no array:"
        );

        int pesquisar = scanf.nextInt();

        System.out.println(
                "//----------------------------------------------//"
        );

        System.out.println("Array ordenado:");

        array.printArray();

        boolean encontrado = pesquisaSequencial(
                array,
                pesquisar,
                array.getTam()
        );

        if (encontrado) {
            System.out.println("Está presente no array");
        } else {
            System.out.println("Não está presente no array");
        }

        System.out.println(
                "Foram feitas " + comparacoes + " comparações"
        );


        System.out.println(
                "//----------------------------------------------//"
        );

        System.out.println(
                "A busca sequencial possui custo Θ(n)."
        );

        System.out.println(
                "O array pode estar ordenado ou desordenado."
        );


        System.out.println(
                "//----------------------------------------------//"
        );


        // Embaralha o array
        array.customShuffle();

        System.out.println(
                "Pesquisando agora em um array desordenado:"
        );

        array.printArray();

        comparacoes = 0;

        encontrado = pesquisaSequencial(
                array,
                pesquisar,
                array.getTam()
        );

        if (encontrado) {
            System.out.println("Está presente no array");
        } else {
            System.out.println("Não está presente no array");
        }

        System.out.println(
                "Foram feitas " + comparacoes + " comparações"
        );
    }


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String args[]) {

        Scanner scanf = new Scanner(System.in);

        // Pede o tamanho do array
        System.out.print(
                "Digite o tamanho do array que deseja: "
        );

        int tam = scanf.nextInt();

        // Cria o array
        CustomArray array = new CustomArray(tam);


        // Escolhe o algoritmo
        System.out.println(
                "(1) Pesquisa Sequencial\n" +
                "(2) Pesquisa Binária\n" +
                "Digite qual deseja utilizar:"
        );

        int choose = scanf.nextInt();


        switch (choose) {

            case 1:

                pesquisaSequencial(array, scanf);

                break;


            case 2:

                pesquisaBinaria(array, scanf);

                break;


            default:

                System.out.println("Opção inválida.");

                break;
        }

        scanf.close();
    }
}