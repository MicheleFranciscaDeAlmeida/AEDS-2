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

        Scanner sc = new Scanner(System.in);
        ListaDupla lista = new ListaDupla();

        // Leitura dos IDs iniciais
        while (sc.hasNextInt()) {
            int id = sc.nextInt();

            if (id == -1) {
                break;
            }

            Veiculo encontrado = buscarVeiculo(frota, id);

            if (encontrado != null) {
                lista.inserirFim(encontrado);
            }
        }

        // Quantidade de comandos
        int quantidade = sc.nextInt();

        for (int i = 0; i < quantidade; i++) {

            String comando = sc.next();

            if (comando.equals("II")) {

                int id = sc.nextInt();
                Veiculo v = buscarVeiculo(frota, id);
                lista.inserirInicio(v);

            } else if (comando.equals("IF")) {

                int id = sc.nextInt();
                Veiculo v = buscarVeiculo(frota, id);
                lista.inserirFim(v);

            } else if (comando.equals("I*")) {

                int posicao = sc.nextInt();
                int id = sc.nextInt();

                Veiculo v = buscarVeiculo(frota, id);
                lista.inserir(v, posicao);

            } else if (comando.equals("RI")) {

                Veiculo removido = lista.removerInicio();
                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());

            } else if (comando.equals("RF")) {

                Veiculo removido = lista.removerFim();
                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());

            } else if (comando.equals("R*")) {

                int posicao = sc.nextInt();

                Veiculo removido = lista.remover(posicao);
                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());
            }
        }

        lista.mostrar();

        sc.close();
    }
}