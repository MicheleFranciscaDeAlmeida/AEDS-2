import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Representa uma data com dia, mes e ano
class Data {
private int dia;
private int mes;
private int ano;

// Construtor da classe Data
public Data(int dia, int mes, int ano) {
	this.dia = dia;
	this.mes = mes;
	this.ano = ano;
}

// Método parseData para converter uma data no formato AAAA-MM-DD
// para um objeto Data
public static Data parseData(String s) {
	if(s == null || s.isEmpty()) return new Data(0,0,0);
	// Separa a data no formato AAAA-MM-DD em suas partes
	String[] partes = s.split("-");
	if (partes.length == 3) {
		try {
			return new Data(
					Integer.parseInt(partes[2].trim()),
					Integer.parseInt(partes[1].trim()),
					Integer.parseInt(partes[0].trim()));
		} catch (Exception e) {
			return new Data(0,0,0);
		}

	}
	return new Data(0,0,0);
}

// Método format
public String format() {
	return String.format("%02d/%02d/%04d", dia, mes, ano);
}

public int getDia() {
	return dia;
}

public void setDia(int dia) {
	this.dia = dia;
}

public int getMes() {
	return mes;
}

public void setMes(int mes) {
	this.mes = mes;
}

public int getAno() {
	return ano;
}

public void setAno(int ano) {
	this.ano = ano;
}
}



// Atributos do veiculo
class Veiculo {
	private int id;
	private String marca;
	private String modelo;
	private int ano;
	private String categoria;
	private String[] combustivel;
	private String transmissao;
	private String tracao;
	private int cilindros;
	private double cilindrada;
	private double consumoCidade;
	private double consumoEstrada;
	private double co2;
	private boolean turbo;
	private Data dataRegistro;

	// Construtor do veiculo
	public Veiculo(int id, String marca, String modelo, int ano, String categoria, String[] combustivel, String transmissao, String tracao, int cilindros, double cilindrada, double consumoCidade, double consumoEstrada, double co2, boolean turbo, Data dataRegistro) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.categoria = categoria;
		this.combustivel = combustivel;
		this.transmissao = transmissao;
		this.tracao = tracao;
		this.cilindros = cilindros;
		this.cilindrada = cilindrada;
		this.consumoCidade = consumoCidade;
		this.consumoEstrada = consumoEstrada;
		this.co2 = co2;
		this.turbo = turbo;
		this.dataRegistro = dataRegistro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String[] getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String[] combustivel) {
		this.combustivel = combustivel;
	}
	public int getCilindros() {
		return cilindros;
	}
	public void setCilindros(int cilindros) {
		this.cilindros = cilindros;
	}
	public double getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}
	public String getTransmissao() {
		return transmissao; 
	}
	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}
	public String getTracao() {
		return tracao;
	}
	public void setTracao(String tracao) {
		this.tracao = tracao;
	}
	public double getConsumoCidade() {
		return consumoCidade;
	}
	public void setConsumoCidade(double consumoCidade) {
		this.consumoCidade = consumoCidade;
	}
	public double getConsumoEstrada() {
		return consumoEstrada;
	}
	public void setConsumoEstrada(double consumoEstrada) {
		this.consumoEstrada = consumoEstrada;
	}
	public double getCo2() {
		return co2;
	}
	public void setCo2(double co2) {
		this.co2 = co2;
	}
	public boolean getTurbo() {
		return turbo;
	}
	public void setTurbo(boolean turbo) {
		this.turbo = turbo;
	}
	public Data getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Data dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	// Formata os dados do veiculo conforme o padrao do TP
	public String format() {
		// Converte o array de combustiveis para String legivel
		String combStr = "";

		if (combustivel != null) {
			for (int i = 0; i < combustivel.length; i++) {
				combStr += combustivel[i];
				if (i < combustivel.length - 1) {

					// Adiciona virgula se nao for o ultimo
					combStr += ",";
				}
			}
		}
		return String.format(Locale.US,"[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]", id, marca, modelo, ano, categoria, combStr, cilindros, cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, turbo, (dataRegistro != null ? dataRegistro.format() : "N/A" )); 
	}

	// Implementacao de InsertionSort
	public static void insertionSort(Veiculo[] veiculos, int n) {
	for (int i = 1; i < n; i++) {
		Veiculo chave = veiculos[i];
		int j = i - 1;

		while (j >= 0 && veiculos[j].getMarca().compareToIgnoreCase(chave.getMarca()) > 0) {
			veiculos[j + 1] = veiculos[j];
			j--;
		}

		veiculos[j + 1] = chave;
	}

     }
}

class LeitorCsv {
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



public class Main {
	public static void main(String[] args) {

		//Carrega o dataset
		Veiculo[] frota = LeitorCsv.ler("/tmp/veiculos.csv");

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

			// Ordena os veiculos selecionados pela marca
			Veiculo.insertionSort(veiculosSelecionados, quantidade);

			//Saida formatada
			for (int i = 0; i < quantidade; i++) {
				System.out.println(veiculosSelecionados[i].format());
			}

			sc.close();

	        }
	 }


/*
 * Uso de IA:
 * A ferramenta de Inteligência Artificial foi utilizada como apoio
 * para fundamentação, documentação e compreensão do enunciado,
 * auxiliando na análise da lógica e na revisão do código.
 *
 * A implementação foi desenvolvida a partir do meu próprio
 * raciocínio e entendimento do problema, com testes e validação
 * realizados por mim.
 */
