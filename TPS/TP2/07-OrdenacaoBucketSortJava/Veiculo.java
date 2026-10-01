import java.util.ArrayList;
import java.util.Locale;

// Atributos do veiculo
public class Veiculo {
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

	// InsertionSort usado dentro de cada balde 
	private static void insertionSortBalde(ArrayList<Veiculo> balde) {
	for (int i = 1; i < balde.size(); i++) {
		Veiculo chave = balde.get(i);
		int j = i - 1;

		while (j >= 0 && balde.get(j).getCilindrada() > chave.getCilindrada()) {
			balde.set(j + 1, balde.get(j));
			j--;
		}

		balde.set(j + 1, chave);
	}

     }

     // Implementacao do Bucket Sort
     public static void bucketSort(Veiculo[] veiculos, int n) {
	     if (n <= 1) {
		   return;

     }

     // Criacao dos 10 baldes
     ArrayList<ArrayList<Veiculo>> baldes = new ArrayList<>();

     for (int i = 0; i < 10; i++) {
	     baldes.add(new ArrayList<>());
     }

     // Distribuicao dos veiculos nos baldes
     for (int i = 0; i < n; i++) {
	    int indice = (int) ((veiculos[i].getCilindrada() / 8.1) * 10);
	   if (indice >= 10) {
		  indice = 9;
	   }

	  baldes.get(indice).add(veiculos[i]);

     } 

     // Ordenacao de cada balde
     for (int i = 0; i < 10; i++) {
	     insertionSortBalde(baldes.get(i));
     }

     // Concatenacao dos baldes 
     int posicao = 0;

     for (int i = 0; i < 10; i++) {
	     for (Veiculo v : baldes.get(i)) {
		     veiculos[posicao] = v;
		     posicao++;
	     }
	   }
     }	     
}
