import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorCsv {
	// Converte uma linha do CSV em um objeto Veiculo
	public static Veiculo parseVeiculo(String s) {
		if (s == null || s.isEmpty()) {
			return null;
		}
		// Separa os campos da linha usando a virgula
		String[] campos = s.split(",");

		if (campos.length < 15) {
			return null;
		}
			try {
				int id = Integer.parseInt(campos[0].trim());
				String marca = campos[1].trim();
				String modelo = campos[2].trim();
				int ano = Integer.parseInt(campos[3].trim());
				String categoria = campos[4].trim();

				// Separa os diferentes tipos de combustiveis usando o split
				String[] combustiveis = campos[5].trim().split(";");
				for (int i = 0; i < combustiveis.length; i++) {
					combustiveis[i] = combustiveis[i].trim();
				}
				
				// Converte os campos numericos para seus respectivos tipos
				int cilindros = Integer.parseInt(campos[6].trim());
				double cilindrada = Double.parseDouble(campos[7].trim().replace(',', '.'));
				String transmissao = campos[8].trim();
				String tracao = campos[9].trim();
				double consumoCidade = Double.parseDouble(campos[10].trim().replace(',', '.'));
				double consumoEstrada = Double.parseDouble(campos[11].trim().replace(',', '.'));
				double co2 = Double.parseDouble(campos[12].trim().replace(',', '.'));
				boolean turbo = campos[13].trim().equalsIgnoreCase("true");
				Data data = Data.parseData(campos[14].trim());

					return new Veiculo(id, marca, modelo, ano, categoria, combustiveis, transmissao, tracao, cilindros, cilindrada, consumoCidade, consumoEstrada, co2, turbo, data);
			} catch (Exception e) {
				return null;
			}
		}
// Metodo 2 - faz a leitura do arquivo e retorna o array de veiculos
public static Veiculo[] ler(String caminhoArquivo) {

	// Array grande o suficiente para o dataset
	Veiculo[] temp = new Veiculo[10000];
	int count = 0;

	try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
		String linha;
		boolean primeiraLinha = true;

		while ((linha = br.readLine()) != null) {
			if (primeiraLinha) {
				primeiraLinha = false;
				continue;
			}
			
			//Chama o metodo parseVeiculo
			Veiculo v = parseVeiculo(linha);
			if (v != null) {
				if (count < temp.length) {
					temp[count] = v;
					count++;
				}
			}
		}
	} catch (IOException e) {
		System.err.println("Erro ao ler o arquivo: " + e.getMessage());
	}

	//Criacao do array final no tamanho dos dados lidos
	Veiculo[] resultado = new Veiculo[count];

	for (int i = 0; i < count; i++) {
		resultado[i] = temp[i];
	}

	return resultado;
}
}
