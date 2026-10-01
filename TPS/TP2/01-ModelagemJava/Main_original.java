import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		//Carrega o dataset
		Veiculo[] frota = LeitorCsv.ler("veiculos.csv");

		if (frota.length == 0) {
			System.out.println("Nenhum veiculo carregado.");
			return;
		}

		//Leitura dos IDs da entrada padrao
		Scanner sc = new Scanner(System.in);

		while(sc.hasNextLine()) {
			int idBusca = sc.nextInt();

			if (idBusca == -1) {
				break;
			}

			//Pesquisa Sequencial
			Veiculo encontrado = null;

			for (Veiculo v : frota) {
				if (v.getId() == idBusca) {
					encontrado = v;
					break;
				}
			   }

			//Saida formatada
			if (encontrado != null) {
				System.out.println(encontrado.format());
			} else {
				System.out.println("Veiculo nao encontrado.");
			}
		    }
		    sc.close();

		   }
	  }
