public class Fila
{
    private int[] array;
    private int primeiro;
    private int ultimo;

    public Fila()
    {
        this(5);
    }

    public Fila(int tamanho)
    {
        array = new int[tamanho];
        primeiro = 0;
        ultimo = 0;
    }

    public void inserir(int x) throws Exception
    {
        if(ultimo >= array.length)
        {
            throw new Exception("Erro ao inserir: fila cheia");
        }

        array[ultimo] = x;
        ultimo++;
    }

    public int remover() throws Exception
    {
        if(primeiro == ultimo)
        {
            throw new Exception("Erro ao remover: fila vazia");
        }

        int resp = array[primeiro];
        primeiro++;

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        for(int i = primeiro; i < ultimo; i++)
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

// FILA = FIFO First In First Out
// O primeiro elemento a ser removido é o primeiro que foi inserido.
// A fila é uma estrutura de dados linear que segue o princípio FIFO (First In, First Out),
// ou seja, o primeiro elemento inserido é o primeiro a ser removido. Ela é
// frequentemente usada em situações onde é necessário processar dados na ordem
// em que foram recebidos, como em filas de impressão, filas de atendimento ao cliente, 
// entre outros.
// Usamos a analogia de uma fila de pessoas aguardando pra serem atendidas em um banco.
// A primeira pessoa a entrar na fila é a primeira a ser atendida,
// e a última pessoa a entrar na fila é a última a ser atendida.