public class Main
{
    public static void main(String[] args) throws Exception
    {
        Pilha pilha = new Pilha();

        pilha.inserir(10);
        pilha.inserir(20);
        pilha.inserir(30);

        System.out.println("Pilha:");
        pilha.mostrar();

        int removido = pilha.remover();

        System.out.println("Elemento removido: " + removido);

        pilha.mostrar();
    }
}