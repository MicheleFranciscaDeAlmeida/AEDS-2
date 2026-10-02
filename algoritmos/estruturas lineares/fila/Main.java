public class Main
{
    public static void main(String[] args) throws Exception
    {
        Fila fila = new Fila();

        fila.inserir(5);
        fila.inserir(7);
        fila.inserir(8);

        System.out.println("Fila após inserções:");
        fila.mostrar();

        int removido = fila.remover();

        System.out.println("Elemento removido: " + removido);

        System.out.println("Fila após remoção:");
        fila.mostrar();
    }
}