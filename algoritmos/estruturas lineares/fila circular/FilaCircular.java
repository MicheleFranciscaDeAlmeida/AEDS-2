public class FilaCircular
{
    private int[] array;
    private int primeiro;
    private int ultimo;

    public FilaCircular()
    {
        this(5);
    }

    public FilaCircular(int tamanho)
    {
        // Usamos uma posição extra para diferenciar
        // fila cheia de fila vazia.
        array = new int[tamanho + 1];

        primeiro = 0;
        ultimo = 0;
    }

    public void inserir(int x) throws Exception
    {
        // Se a próxima posição de ultimo for igual a primeiro,
        // significa que a fila está cheia.
        if((ultimo + 1) % array.length == primeiro)
        {
            throw new Exception("Erro ao inserir: fila cheia");
        }

        array[ultimo] = x;

        // Avança circularmente.
        ultimo = (ultimo + 1) % array.length;
    }

    public int remover() throws Exception
    {
        if(primeiro == ultimo)
        {
            throw new Exception("Erro ao remover: fila vazia");
        }

        int resp = array[primeiro];

        // Avança circularmente.
        primeiro = (primeiro + 1) % array.length;

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        for(int i = primeiro; i != ultimo; i = (i + 1) % array.length)
        {
            System.out.print(array[i] + " ");
        }

        System.out.println("]");
    }

    public boolean isVazia()
    {
        return primeiro == ultimo;
    }
}

// FILA CIRCULAR = FIFO (First In, First Out)
//
// A fila circular é uma estrutura de dados linear que segue o princípio
// FIFO (First In, First Out), ou seja, o primeiro elemento inserido é o
// primeiro elemento a ser removido.
//
// A principal diferença em relação a uma fila implementada de forma linear
// é que as posições liberadas no início do array podem ser reutilizadas.
//
// Quando o índice de inserção ou remoção chega ao final do array, ele retorna
// para o início, fazendo com que a estrutura "circule" pelo array.
//
// Esse comportamento é implementado utilizando aritmética modular (%),
// permitindo calcular a próxima posição de forma circular.
//
// Exemplo:
//
//      [10] [20] [30] [40] [  ]
//       ↑              ↑
//    primeiro        ultimo
//
// Após remover 10:
//
//      [  ] [20] [30] [40] [  ]
//            ↑              ↑
//         primeiro        ultimo
//
// Uma nova inserção pode reutilizar a posição que ficou livre:
//
//      [50] [20] [30] [40] [  ]
//
// Principais características:
// - segue o princípio FIFO;
// - utiliza um array de forma circular;
// - permite reutilizar posições liberadas;
// - utiliza aritmética modular para controlar os índices;
// - evita o deslocamento dos elementos após uma remoção.