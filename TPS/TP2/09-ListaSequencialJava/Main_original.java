import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Veiculo[] frota = LeitorCsv.ler("veiculos.csv");

        Scanner sc = new Scanner(System.in);

        Lista lista = new Lista();

        // Leitura dos IDs iniciais.
        while (sc.hasNextInt()) {
            int idBusca = sc.nextInt();

            if (idBusca == -1) {
                break;
            }

            for (Veiculo v : frota) {
                if (v.getId() == idBusca) {
                    lista.inserirFim(v);
                    break;
                }
            }
        }

        // Quantidade de comandos.
        int quantidadeComandos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < quantidadeComandos; i++) {

            String linha = sc.nextLine();
            String[] partes = linha.split(" ");

            String comando = partes[0];

            if (comando.equals("II")) {

                int id = Integer.parseInt(partes[1]);

                for (Veiculo v : frota) {
                    if (v.getId() == id) {
                        lista.inserirInicio(v);
                        break;
                    }
                }

            } else if (comando.equals("I*")) {

                int posicao = Integer.parseInt(partes[1]);
                int id = Integer.parseInt(partes[2]);

                for (Veiculo v : frota) {
                    if (v.getId() == id) {
                        lista.inserir(v, posicao);
                        break;
                    }
                }

            } else if (comando.equals("IF")) {

                int id = Integer.parseInt(partes[1]);

                for (Veiculo v : frota) {
                    if (v.getId() == id) {
                        lista.inserirFim(v);
                        break;
                    }
                }

            } else if (comando.equals("RI")) {

                Veiculo removido = lista.removerInicio();

                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());

            } else if (comando.equals("R*")) {

                int posicao = Integer.parseInt(partes[1]);

                Veiculo removido = lista.remover(posicao);

                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());

            } else if (comando.equals("RF")) {

                Veiculo removido = lista.removerFim();

            System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());
			
            }
        }

        lista.mostrar();

        sc.close();
    }
}