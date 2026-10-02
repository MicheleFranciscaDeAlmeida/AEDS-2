public class Main
{
    public static void main(String[] args) throws Exception
    {
        Lista lista = new Lista();

        lista.inserirFim(10);
        lista.inserirFim(20);
        lista.inserirFim(30);

        lista.inserirInicio(5);

        System.out.println("Lista:");
        lista.mostrar();

        System.out.println("Removido do início: " + lista.removerInicio());
        System.out.println("Removido do fim: " + lista.removerFim());

        lista.mostrar();
    }
}