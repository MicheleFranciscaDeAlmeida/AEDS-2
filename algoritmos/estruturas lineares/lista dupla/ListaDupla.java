class Celula
{
    public int elemento;
    public Celula ant;
    public Celula prox;

    public Celula(int elemento)
    {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }
}

public class ListaDupla
{
    private Celula primeiro;
    private Celula ultimo;

    public ListaDupla()
    {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(int x)
    {
        Celula nova = new Celula(x);

        if(primeiro == null)
        {
            primeiro = nova;
            ultimo = nova;
        }
        else
        {
            nova.prox = primeiro;
            primeiro.ant = nova;
            primeiro = nova;
        }
    }

    public void inserirFim(int x)
    {
        Celula nova = new Celula(x);

        if(ultimo == null)
        {
            primeiro = nova;
            ultimo = nova;
        }
        else
        {
            nova.ant = ultimo;
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
        else
        {
            primeiro.ant = null;
        }

        return resp;
    }

    public int removerFim() throws Exception
    {
        if(ultimo == null)
        {
            throw new Exception("Lista vazia");
        }

        int resp = ultimo.elemento;

        ultimo = ultimo.ant;

        if(ultimo == null)
        {
            primeiro = null;
        }
        else
        {
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

    public void mostrarInverso()
    {
        System.out.print("[ ");

        Celula i = ultimo;

        while(i != null)
        {
            System.out.print(i.elemento + " ");
            i = i.ant;
        }

        System.out.println("]");
    }
}

// LISTA DUPLA
// A lista duplamente encadeada é uma estrutura de dados linear que consiste em uma
// sequência de elementos, onde cada elemento (ou nó) contém um valor e duas referências
// (ou ponteiros): uma para o próximo elemento e outra para o elemento anterior na sequência.