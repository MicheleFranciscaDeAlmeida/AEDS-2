import java.util.Scanner;

public class Main {

    public static Veiculo buscarVeiculo(Veiculo[] frota, int id) {

        for (Veiculo v : frota) {
            if (v.getId() == id) {
                return v;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Veiculo[] frota = LeitorCsv.ler("veiculos.csv");

        if (frota.length == 0) {
            System.out.println("Nenhum veiculo carregado.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha();

        // IDs iniciais.
        while (true) {

            int id = sc.nextInt();

            if (id == -1) {
                break;
            }

            Veiculo veiculo = buscarVeiculo(frota, id);

            if (veiculo != null) {
                pilha.inserir(veiculo);
            }
        }

        int quantidadeComandos = sc.nextInt();

        for (int i = 0; i < quantidadeComandos; i++) {

            String comando = sc.next();

            if (comando.equals("I")) {

                int id = sc.nextInt();

                Veiculo veiculo = buscarVeiculo(frota, id);

                if (veiculo != null) {
                    pilha.inserir(veiculo);
                }

            } else if (comando.equals("R")) {

                Veiculo removido = pilha.remover();

                if (removido != null) {
                    System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());
                }
            }
        }

        pilha.mostrar();

        sc.close();
    }
}
