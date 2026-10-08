import java.util.Scanner;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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

class ListaDupla {

    private Celula primeiro;
    private Celula ultimo;

    private class Celula {
        Veiculo elemento;
        Celula ant;
        Celula prox;

        Celula(Veiculo elemento) {
            this.elemento = elemento;
            this.ant = null;
            this.prox = null;
        }
    }

    public ListaDupla() {
        primeiro = null;
        ultimo = null;
    }

    public void inserirInicio(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);

        nova.prox = primeiro;

        if (primeiro != null) {
            primeiro.ant = nova;
        } else {
            ultimo = nova;
        }

        primeiro = nova;
    }

    public void inserirFim(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);

        nova.ant = ultimo;

        if (ultimo != null) {
            ultimo.prox = nova;
        } else {
            primeiro = nova;
        }

        ultimo = nova;
    }

    public void inserir(Veiculo veiculo, int posicao) {

        if (posicao <= 0) {
            inserirInicio(veiculo);
            return;
        }

        if (primeiro == null) {
            inserirFim(veiculo);
            return;
        }

        Celula atual = primeiro;

        for (int i = 0; i < posicao && atual != null; i++) {
            atual = atual.prox;
        }

        if (atual == null) {
            inserirFim(veiculo);
            return;
        }

        Celula nova = new Celula(veiculo);

        nova.ant = atual.ant;
        nova.prox = atual;

        atual.ant.prox = nova;
        atual.ant = nova;
    }

    public Veiculo removerInicio() {

        Veiculo removido = primeiro.elemento;

        primeiro = primeiro.prox;

        if (primeiro != null) {
            primeiro.ant = null;
        } else {
            ultimo = null;
        }

        return removido;
    }

    public Veiculo removerFim() {

        Veiculo removido = ultimo.elemento;

        ultimo = ultimo.ant;

        if (ultimo != null) {
            ultimo.prox = null;
        } else {
            primeiro = null;
        }

        return removido;
    }

    public Veiculo remover(int posicao) {

        if (posicao <= 0) {
            return removerInicio();
        }

        Celula atual = primeiro;

        for (int i = 0; i < posicao && atual != null; i++) {
            atual = atual.prox;
        }

        if (atual == ultimo) {
            return removerFim();
        }

        Veiculo removido = atual.elemento;

        atual.ant.prox = atual.prox;
        atual.prox.ant = atual.ant;

        return removido;
    }

    public void mostrar() {

        Celula atual = primeiro;

        while (atual != null) {
            System.out.println(atual.elemento.format());
            atual = atual.prox;
        }
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
}

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

	String[] caminhos = {
		caminhoArquivo,
		"../01-ModelagemJava/veiculos.csv",
		"01-ModelagemJava/veiculos.csv",
		"veiculos.csv",
		"../veiculos.csv",
		"../../01-ModelagemJava/veiculos.csv",
		"../../veiculos.csv",
		"/tmp/veiculos.csv"
	};

	String caminhoEncontrado = null;

	// Procura o dataset nos caminhos possiveis.
	for (String caminho : caminhos) {
		try (BufferedReader teste = new BufferedReader(new FileReader(caminho))) {
			caminhoEncontrado = caminho;
			break;
		} catch (IOException e) {
			// Tenta o proximo caminho.
		}
	}

	if (caminhoEncontrado == null) {
		System.err.println("Erro: Nao foi possivel abrir o arquivo veiculos.csv");
		return new Veiculo[0];
	}

	// Array grande o suficiente para o dataset.
	Veiculo[] temp = new Veiculo[10000];
	int count = 0;

	try (BufferedReader br = new BufferedReader(new FileReader(caminhoEncontrado))) {
		String linha;
		boolean primeiraLinha = true;

		while ((linha = br.readLine()) != null) {
			if (primeiraLinha) {
				primeiraLinha = false;
				continue;
			}

			// Chama o metodo parseVeiculo.
			Veiculo v = parseVeiculo(linha);

			if (v != null && count < temp.length) {
				temp[count] = v;
				count++;
			}
		}

	} catch (IOException e) {
		System.err.println("Erro ao ler o arquivo: " + e.getMessage());
	}

	// Criacao do array final no tamanho dos dados lidos.
	Veiculo[] resultado = new Veiculo[count];

	for (int i = 0; i < count; i++) {
		resultado[i] = temp[i];
	}

	return resultado;
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
