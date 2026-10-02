public class Lista
{
    private int[] array;
    private int n;

    public Lista()
    {
        this(5);
    }

    public Lista(int tamanho)
    {
        array = new int[tamanho];
        n = 0;
    }

    public void inserirFim(int x) throws Exception
    {
        if(n >= array.length)
        {
            throw new Exception("Lista cheia");
        }

        array[n] = x;
        n++;
    }

    public void inserirInicio(int x) throws Exception
    {
        if(n >= array.length)
        {
            throw new Exception("Lista cheia");
        }

        for(int i = n; i > 0; i--)
        {
            array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }

    public void inserir(int pos, int x) throws Exception
    {
        if(n >= array.length)
        {
            throw new Exception("Lista cheia");
        }

        if(pos < 0 || pos > n)
        {
            throw new Exception("Posição inválida");
        }

        for(int i = n; i > pos; i--)
        {
            array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    public int removerInicio() throws Exception
    {
        if(n == 0)
        {
            throw new Exception("Lista vazia");
        }

        int resp = array[0];

        n--;

        for(int i = 0; i < n; i++)
        {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public int removerFim() throws Exception
    {
        if(n == 0)
        {
            throw new Exception("Lista vazia");
        }

        n--;

        return array[n];
    }

    public int remover(int pos) throws Exception
    {
        if(n == 0)
        {
            throw new Exception("Lista vazia");
        }

        if(pos < 0 || pos >= n)
        {
            throw new Exception("Posição inválida");
        }

        int resp = array[pos];

        n--;

        for(int i = pos; i < n; i++)
        {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        for(int i = 0; i < n; i++)
        {
            System.out.print(array[i] + " ");
        }

        System.out.println("]");
    }
}

// LISTA SEQUENCIAL
//
// A lista sequencial é uma estrutura de dados linear que armazena seus
// elementos em posições contíguas de memória, geralmente utilizando um array.
//
// Os elementos podem ser acessados diretamente através de índices, permitindo
// acesso rápido a uma determinada posição da lista.
//
// A inserção e a remoção de elementos podem exigir o deslocamento de vários
// elementos para manter a ordem da lista. Por exemplo, ao inserir um elemento
// no início, os elementos existentes precisam ser deslocados para a direita.
//
// Quando implementada utilizando um array de tamanho fixo, a lista possui uma
// capacidade máxima definida durante sua criação.
//
// Exemplo:
//
//      índice:   0    1    2    3
//              [10] [20] [30] [40]
//
// O acesso ao elemento de índice 2 é realizado diretamente:
//
//      array[2] -> 30
//
// Principais características:
// - elementos armazenados em posições contíguas;
// - acesso direto através de índices;
// - inserções e remoções podem exigir deslocamentos;
// - implementação simples utilizando arrays.