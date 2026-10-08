public class ListaDupla {

    private Celula primeiro;
    private Celula ultimo;

    private class Celula {
        Veiculo elemento;
        Celula ant;
        Celula prox;

        Celula(Veiculo elemento) {
            this.elemento = elemento;
            this.ant = null;
            this.prox = null;
        }
    }

    public ListaDupla() {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);

        nova.prox = primeiro;

        if (primeiro != null) {
            primeiro.ant = nova;
        } else {
            ultimo = nova;
        }

        primeiro = nova;
    }

    public void inserirFim(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);

        nova.ant = ultimo;

        if (ultimo != null) {
            ultimo.prox = nova;
        } else {
            primeiro = nova;
        }

        ultimo = nova;
    }

    public void inserir(Veiculo veiculo, int posicao) {

        if (posicao <= 0) {
            inserirInicio(veiculo);
            return;
        }

        if (primeiro == null) {
            inserirFim(veiculo);
            return;
        }

        Celula atual = primeiro;

        for (int i = 0; i < posicao && atual != null; i++) {
            atual = atual.prox;
        }

        if (atual == null) {
            inserirFim(veiculo);
            return;
        }

        Celula nova = new Celula(veiculo);

        nova.ant = atual.ant;
        nova.prox = atual;

        atual.ant.prox = nova;
        atual.ant = nova;
    }

    public Veiculo removerInicio() {

        Veiculo removido = primeiro.elemento;

        primeiro = primeiro.prox;

        if (primeiro != null) {
            primeiro.ant = null;
        } else {
            ultimo = null;
        }

        return removido;
    }

    public Veiculo removerFim() {

        Veiculo removido = ultimo.elemento;

        ultimo = ultimo.ant;

        if (ultimo != null) {
            ultimo.prox = null;
        } else {
            primeiro = null;
        }

        return removido;
    }

    public Veiculo remover(int posicao) {

        if (posicao <= 0) {
            return removerInicio();
        }

        Celula atual = primeiro;

        for (int i = 0; i < posicao && atual != null; i++) {
            atual = atual.prox;
        }

        if (atual == ultimo) {
            return removerFim();
        }

        Veiculo removido = atual.elemento;

        atual.ant.prox = atual.prox;
        atual.prox.ant = atual.ant;

        return removido;
    }

    public void mostrar() {

        Celula atual = primeiro;

        while (atual != null) {
            System.out.println(atual.elemento.format());
            atual = atual.prox;
        }
    }
}
