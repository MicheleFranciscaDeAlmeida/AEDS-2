class CelulaDupla
{
    public int elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla(int elemento)
    {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }
}

public class ListaDuplaFlexivel
{
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public ListaDuplaFlexivel()
    {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(int x)
    {
        CelulaDupla nova = new CelulaDupla(x);

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
        CelulaDupla nova = new CelulaDupla(x);

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

    public void inserir(int pos, int x) throws Exception
    {
        if(pos < 0)
        {
            throw new Exception("Posição inválida");
        }

        if(pos == 0)
        {
            inserirInicio(x);
            return;
        }

        CelulaDupla i = primeiro;

        for(int j = 0; j < pos - 1 && i != null; j++)
        {
            i = i.prox;
        }

        if(i == null)
        {
            throw new Exception("Posição inválida");
        }

        if(i == ultimo)
        {
            inserirFim(x);
            return;
        }

        CelulaDupla nova = new CelulaDupla(x);

        nova.prox = i.prox;
        nova.ant = i;

        i.prox.ant = nova;
        i.prox = nova;
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

    public int remover(int pos) throws Exception
    {
        if(primeiro == null)
        {
            throw new Exception("Lista vazia");
        }

        if(pos < 0)
        {
            throw new Exception("Posição inválida");
        }

        if(pos == 0)
        {
            return removerInicio();
        }

        CelulaDupla i = primeiro;

        for(int j = 0; j < pos && i != null; j++)
        {
            i = i.prox;
        }

        if(i == null)
        {
            throw new Exception("Posição inválida");
        }

        if(i == ultimo)
        {
            return removerFim();
        }

        int resp = i.elemento;

        i.ant.prox = i.prox;
        i.prox.ant = i.ant;

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        CelulaDupla i = primeiro;

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

        CelulaDupla i = ultimo;

        while(i != null)
        {
            System.out.print(i.elemento + " ");
            i = i.ant;
        }

        System.out.println("]");
    }
}

// LISTA DUPLA FLEXÍVEL 
// A lista duplamente encadeada flexível é uma estrutura de dados linear
// formada por nós. Cada nó possui três partes: 
// 1. elemento: armazena o valor;
// 2. ant: referência para o nó anterior;
// 3. prox: referência para o próximo nó.
// A lista possui duas referências principais:
// - primeiro: aponta para o primeiro nó;
// - ultimo: aponta para o último nó.
// Como a estrutura é flexível, os nós são criados dinamicamente conforme
// novos elementos são inseridos. Diferentemente de uma lista sequencial,
// não é necessário reservar previamente um vetor com tamanho fixo.
// A existência das referências ant e prox permite percorrer a lista nos
// dois sentidos: do primeiro para o último e do último para o primeiro.
// Principais operações: // // inserirInicio() -> insere um elemento no início da lista.
// inserirFim() -> insere um elemento no final da lista.
// inserir() -> insere um elemento em determinada posição.
// removerInicio() -> remove o primeiro elemento.
// removerFim() -> remove o último elemento.
// remover() -> remove um elemento de determinada posição.
// mostrar() -> percorre a lista do início para o fim.
// mostrarInverso()-> percorre a lista do fim para o início.
// A principal diferença para uma lista sequencial é que os elementos não
// precisam estar armazenados em posições consecutivas da memória.
// Os nós são ligados por referências.
// Exemplo: [10] <-> [20] <-> [30] <-> [40]
// primeiro ultimo
// O símbolo <-> representa as duas ligações: 
// cada nó conhece tanto o seu anterior quanto o seu próximo.