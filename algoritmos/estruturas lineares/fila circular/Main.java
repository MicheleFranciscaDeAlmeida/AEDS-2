public class Main
{
    public static void main(String[] args) throws Exception
    {
        FilaCircular fila = new FilaCircular();

        fila.inserir(5);
        fila.inserir(7);
        fila.inserir(8);
        fila.inserir(9);
        fila.inserir(1);

        System.out.println("Após inserções (5, 7, 8, 9, 1):");
        fila.mostrar();

        int x1 = fila.remover();
        int x2 = fila.remover();

        System.out.println("Após remoções (" + x1 + ", " + x2 + "):");
        fila.mostrar();

        fila.inserir(3);
        fila.inserir(4);

        System.out.println("Após inserções (3, 4):");
        fila.mostrar();

        x1 = fila.remover();
        x2 = fila.remover();
        int x3 = fila.remover();

        System.out.println("Após remoções (" + x1 + ", " + x2 + ", " + x3 + "):");
        fila.mostrar();

        fila.inserir(4);
        fila.inserir(5);

        System.out.println("Após inserções (4, 5):");
        fila.mostrar();

        x1 = fila.remover();
        x2 = fila.remover();

        System.out.println("Após remoções (" + x1 + ", " + x2 + "):");
        fila.mostrar();

        fila.inserir(6);
        fila.inserir(7);

        System.out.println("Após inserções (6, 7):");
        fila.mostrar();

        x1 = fila.remover();

        System.out.println("Após remoção (" + x1 + "):");
        fila.mostrar();
    }
}