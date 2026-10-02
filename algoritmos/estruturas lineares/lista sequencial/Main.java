public class Main
{
    public static void main(String[] args) throws Exception
    {
        Lista lista = new Lista();

        lista.inserirFim(10);
        lista.inserirFim(20);
        lista.inserirFim(30);

        lista.inserirInicio(5);

        lista.inserir(2, 15);

        System.out.println("Lista após inserções:");
        lista.mostrar();

        System.out.println("Removido do início: " + lista.removerInicio());
        System.out.println("Removido do fim: " + lista.removerFim());
        System.out.println("Removido da posição 1: " + lista.remover(1));

        System.out.println("Lista após remoções:");
        lista.mostrar();
    }
}