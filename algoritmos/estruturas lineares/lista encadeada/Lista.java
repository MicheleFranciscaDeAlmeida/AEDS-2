class Celula
{
    public int elemento;
    public Celula prox;

    public Celula()
    {
        this(0);
    }

    public Celula(int elemento)
    {
        this.elemento = elemento;
        this.prox = null;
    }
}

public class Lista
{
    private Celula primeiro;
    private Celula ultimo;

    public Lista()
    {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(int x)
    {
        Celula nova = new Celula(x);

        nova.prox = primeiro;
        primeiro = nova;

        if(ultimo == null)
        {
            ultimo = nova;
        }
    }

    public void inserirFim(int x)
    {
        Celula nova = new Celula(x);

        if(primeiro == null)
        {
            primeiro = nova;
            ultimo = nova;
        }
        else
        {
            ultimo.prox = nova;
            ultimo = nova;
        }
    }

    public int removerInicio() throws Exception
    {
        if(primeiro == null)
        {
            throw new Exception("Lista vazia");
        }

        int resp = primeiro.elemento;

        primeiro = primeiro.prox;

        if(primeiro == null)
        {
            ultimo = null;
        }

        return resp;
    }

    public int removerFim() throws Exception
    {
        if(primeiro == null)
        {
            throw new Exception("Lista vazia");
        }

        int resp = ultimo.elemento;

        if(primeiro == ultimo)
        {
            primeiro = null;
            ultimo = null;
        }
        else
        {
            Celula i = primeiro;

            while(i.prox != ultimo)
            {
                i = i.prox;
            }

            ultimo = i;
            ultimo.prox = null;
        }

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        Celula i = primeiro;

        while(i != null)
        {
            System.out.print(i.elemento + " ");
            i = i.prox;
        }

        System.out.println("]");
    }
}

// LISTA ENCADEADA
//
// A lista encadeada é uma estrutura de dados linear formada por uma sequência
// de nós. Cada nó possui um elemento e uma referência (ou ponteiro) para o
// próximo nó da lista.
//
// Diferentemente da lista sequencial, os elementos não precisam estar
// armazenados em posições contíguas de memória. Cada nó aponta para o próximo,
// formando uma sequência de elementos.
//
// Essa estrutura permite realizar inserções e remoções sem a necessidade de
// deslocar todos os elementos da lista, como acontece em uma lista sequencial.
//
// Porém, o acesso a uma posição específica é mais lento, pois não é possível
// acessar diretamente um elemento pelo seu índice. É necessário percorrer os
// nós a partir do primeiro até chegar à posição desejada.
//
// Exemplo:
//
//      [10] -> [20] -> [30] -> [40] -> NULL
//       ^
//    primeiro
//
// Cada nó possui:
// - elemento: armazena o valor;
// - prox: aponta para o próximo nó.
//
// O último nó aponta para NULL, indicando o final da lista.