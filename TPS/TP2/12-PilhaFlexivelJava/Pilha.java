public class Pilha {

    private Celula topo;

    private class Celula {
        Veiculo elemento;
        Celula prox;

        Celula(Veiculo elemento) {
            this.elemento = elemento;
            this.prox = null;
        }
    }

    public Pilha() {
        topo = null;
    }

    public void inserir(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);
        nova.prox = topo;
        topo = nova;
    }

    public Veiculo remover() {
        if (topo == null) {
            return null;
        }

        Veiculo removido = topo.elemento;
        topo = topo.prox;
        return removido;
    }

    public void mostrar() {
        mostrarRecursivo(topo);
    }

    private void mostrarRecursivo(Celula celula) {
        if (celula != null) {
            System.out.println(celula.elemento.format());
            mostrarRecursivo(celula.prox);
        }
    }
}
