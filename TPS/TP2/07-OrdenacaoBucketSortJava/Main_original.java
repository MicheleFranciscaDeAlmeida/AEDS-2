import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		//Carrega o dataset
		Veiculo[] frota = LeitorCsv.ler("veiculos.csv");

		if (frota.length == 0) {
			System.out.println("Nenhum veiculo carregado.");
			return;
		}

		// Criacao do array para armazenar os veiculos selecionados
		Veiculo[] veiculosSelecionados = new Veiculo[frota.length];
		int quantidade = 0;

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

			if (encontrado != null) {
				// Armazena o veiculo encontrado
				veiculosSelecionados[quantidade] = encontrado;
				quantidade++;
			} else {
				System.out.println("Veiculo nao encontrado.");
			}
		}

			// Ordena os veiculos selecionados por cilindrada usando o Bucket Sort
			Veiculo.bucketSort(veiculosSelecionados, quantidade);

			//Saida formatada
			for (int i = 0; i < quantidade; i++) {
				System.out.println(veiculosSelecionados[i].format());
			}

			sc.close();

	        }
	 }
