public class Pilha
{
    private int[] array;
    private int topo;

    public Pilha()
    {
        this(5);
    }

    public Pilha(int tamanho)
    {
        array = new int[tamanho];
        topo = 0;
    }

    public void inserir(int x) throws Exception
    {
        if(topo >= array.length)
        {
            throw new Exception("Erro ao inserir: pilha cheia");
        }

        array[topo] = x;
        topo++;
    }

    public int remover() throws Exception
    {
        if(topo == 0)
        {
            throw new Exception("Erro ao remover: pilha vazia");
        }

        topo--;

        return array[topo];
    }

    public void mostrar()
    {
        System.out.print("[ ");

        for(int i = topo - 1; i >= 0; i--)
        {
            System.out.print(array[i] + " ");
        }

        System.out.println("]");
    }

    public boolean isVazia()
    {
        return topo == 0;
    }
}


// PILHA = LIFO (Last In, First Out)
//
// A pilha é uma estrutura de dados linear que segue o princípio LIFO
// (Last In, First Out), ou seja, o último elemento inserido é o primeiro
// elemento a ser removido.
//
// Isso significa que as inserções e remoções acontecem sempre no topo
// da pilha.
//
// Principais operações:
//
// inserir() -> adiciona um elemento no topo da pilha.
// remover() -> remove o elemento que está no topo.
// mostrar()  -> percorre a pilha a partir do topo.
//
// Exemplo:
//
//      [30] <- topo
//      [20]
//      [10]
//
// Ao remover(), o elemento 30 será removido primeiro.
//
// Depois:
//
//      [20] <- topo
//      [10]
//
// A pilha é semelhante a uma pilha de pratos para lavar:
// os pratos são colocados um sobre o outro e, na hora de retirar,
// o prato que está por cima é retirado primeiro.
//
// Principais características:
// - segue o princípio LIFO;
// - inserção e remoção acontecem no topo;
// - o último elemento inserido é o primeiro a sair;
// - não é necessário deslocar os demais elementos.
//
// A pilha pode ser implementada utilizando um array ou nós ligados,
// dependendo da implementação escolhida.
