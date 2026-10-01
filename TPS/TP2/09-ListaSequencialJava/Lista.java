public class Lista {

    private Veiculo[] array;
    private int n;

    public Lista() {
        this.array = new Veiculo[100];
        this.n = 0;
    }

    public void inserirInicio(Veiculo veiculo) {
        if (n >= array.length) {
            return;
        }

        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = veiculo;
        n++;
    }

    public void inserir(Veiculo veiculo, int posicao) {
        if (n >= array.length || posicao < 0 || posicao > n) {
            return;
        }

        for (int i = n; i > posicao; i--) {
            array[i] = array[i - 1];
        }

        array[posicao] = veiculo;
        n++;
    }

    public void inserirFim(Veiculo veiculo) {
        if (n >= array.length) {
            return;
        }

        array[n] = veiculo;
        n++;
    }

    public Veiculo removerInicio() {
        if (n == 0) {
            return null;
        }

        Veiculo removido = array[0];

        for (int i = 0; i < n - 1; i++) {
            array[i] = array[i + 1];
        }

        n--;
        array[n] = null;

        return removido;
    }

    public Veiculo remover(int posicao) {
        if (n == 0 || posicao < 0 || posicao >= n) {
            return null;
        }

        Veiculo removido = array[posicao];

        for (int i = posicao; i < n - 1; i++) {
            array[i] = array[i + 1];
        }

        n--;
        array[n] = null;

        return removido;
    }

    public Veiculo removerFim() {
        if (n == 0) {
            return null;
        }

        n--;

        Veiculo removido = array[n];
        array[n] = null;

        return removido;
    }

   public void mostrar() {
    for (int i = 0; i < n; i++) {
        System.out.println(array[i].format());
    }
    
    }
}