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

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // Converte AAAA-MM-DD para um objeto Data
    public static Data parseData(String s) {
        if (s == null || s.isEmpty()) {
            return new Data(0, 0, 0);
        }

        String[] partes = s.split("-");

        if (partes.length == 3) {
            try {
                return new Data(
                    Integer.parseInt(partes[2].trim()),
                    Integer.parseInt(partes[1].trim()),
                    Integer.parseInt(partes[0].trim())
                );
            } catch (Exception e) {
                return new Data(0, 0, 0);
            }
        }

        return new Data(0, 0, 0);
    }

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

// Representa um veiculo
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

    public Veiculo(
        int id,
        String marca,
        String modelo,
        int ano,
        String categoria,
        String[] combustivel,
        String transmissao,
        String tracao,
        int cilindros,
        double cilindrada,
        double consumoCidade,
        double consumoEstrada,
        double co2,
        boolean turbo,
        Data dataRegistro
    ) {
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

    // Formata os dados do veiculo
    public String format() {
        String combStr = "";

        if (combustivel != null) {
            for (int i = 0; i < combustivel.length; i++) {
                combStr += combustivel[i];

                if (i < combustivel.length - 1) {
                    combStr += ",";
                }
            }
        }

        return String.format(
            Locale.US,
            "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
            id,
            marca,
            modelo,
            ano,
            categoria,
            combStr,
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumoCidade,
            consumoEstrada,
            co2,
            turbo,
            (dataRegistro != null ? dataRegistro.format() : "N/A")
        );
    }

    // Heapsort
    public static void heapSort(Veiculo[] veiculos, int n) {

        // Construcao do heap maximo
        for (int tam = 2; tam <= n; tam++) {
            construir(veiculos, tam);
        }

        // Ordenacao do heap
        int tam = n;

        while (tam > 1) {
            swap(veiculos, 0, tam - 1);
            tam--;

            reconstruir(veiculos, tam);
        }
    }

    // Insere o elemento no heap, subindo enquanto for maior que o pai
    private static void construir(Veiculo[] veiculos, int tam) {
        int i = tam - 1;

        while (i > 0) {
            int pai = (i - 1) / 2;

            if (maior(veiculos[i], veiculos[pai])) {
                swap(veiculos, i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    // Reconstrucao do heap (heapify) depois da troca da raiz
    private static void reconstruir(Veiculo[] veiculos, int tam) {
        int i = 0;

        while (hasFilho(i, tam)) {
            int filho = getMaiorFilho(veiculos, i, tam);

            if (maior(veiculos[filho], veiculos[i])) {
                swap(veiculos, i, filho);
                i = filho;
            } else {
                break;
            }
        }
    }

    // Verifica se o elemento possui pelo menos um filho
    private static boolean hasFilho(int i, int tam) {
        return 2 * i + 1 < tam;
    }

    // Retorna o maior dos dois filhos
    private static int getMaiorFilho(
        Veiculo[] veiculos,
        int i,
        int tam
    ) {
        int esquerdo = 2 * i + 1;
        int direito = 2 * i + 2;

        if (direito < tam && maior(veiculos[direito], veiculos[esquerdo])) {
            return direito;
        }

        return esquerdo;
    }

    // Compara primeiro CO2 e depois transmissao
    private static boolean maior(Veiculo a, Veiculo b) {
        if (a.getCo2() > b.getCo2()) {
            return true;
        }

        if (a.getCo2() < b.getCo2()) {
            return false;
        }

        return a.getTransmissao()
                .compareToIgnoreCase(b.getTransmissao()) > 0;
    }

    // Troca dois veiculos de posicao
    private static void swap(Veiculo[] veiculos, int i, int j) {
        Veiculo temp = veiculos[i];
        veiculos[i] = veiculos[j];
        veiculos[j] = temp;
    }
}

// Leitura do arquivo CSV
class LeitorCsv {

    // Converte uma linha do CSV em um objeto Veiculo
    public static Veiculo parseVeiculo(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

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

            String[] combustiveis = campos[5].trim().split(";");

            for (int i = 0; i < combustiveis.length; i++) {
                combustiveis[i] = combustiveis[i].trim();
            }

            int cilindros = Integer.parseInt(campos[6].trim());

            double cilindrada = Double.parseDouble(
                campos[7].trim().replace(',', '.')
            );

            String transmissao = campos[8].trim();
            String tracao = campos[9].trim();

            double consumoCidade = Double.parseDouble(
                campos[10].trim().replace(',', '.')
            );

            double consumoEstrada = Double.parseDouble(
                campos[11].trim().replace(',', '.')
            );

            double co2 = Double.parseDouble(
                campos[12].trim().replace(',', '.')
            );

            boolean turbo = campos[13].trim().equalsIgnoreCase("true");
            Data data = Data.parseData(campos[14].trim());

            return new Veiculo(
                id,
                marca,
                modelo,
                ano,
                categoria,
                combustiveis,
                transmissao,
                tracao,
                cilindros,
                cilindrada,
                consumoCidade,
                consumoEstrada,
                co2,
                turbo,
                data
            );

        } catch (Exception e) {
            return null;
        }
    }

    // Le o arquivo CSV e retorna o array de veiculos
    public static Veiculo[] ler(String caminhoArquivo) {
        Veiculo[] temp = new Veiculo[10000];
        int count = 0;

        try (BufferedReader br = new BufferedReader(
            new FileReader(caminhoArquivo)
        )) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                Veiculo v = parseVeiculo(linha);

                if (v != null && count < temp.length) {
                    temp[count] = v;
                    count++;
                }
            }

        } catch (IOException e) {
            System.err.println(
                "Erro ao ler o arquivo: " + e.getMessage()
            );
        }

        Veiculo[] resultado = new Veiculo[count];

        for (int i = 0; i < count; i++) {
            resultado[i] = temp[i];
        }

        return resultado;
    }
}

// Classe principal
public class Main {

    public static void main(String[] args) {

        // Carrega o dataset
        Veiculo[] frota = LeitorCsv.ler("/tmp/veiculos.csv");

        if (frota.length == 0) {
            System.out.println("Nenhum veiculo carregado.");
            return;
        }

        // Array para armazenar os veiculos selecionados
        Veiculo[] veiculosSelecionados = new Veiculo[frota.length];
        int quantidade = 0;

        // Leitura dos IDs da entrada padrao
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int idBusca = sc.nextInt();

            if (idBusca == -1) {
                break;
            }

            // Pesquisa sequencial
            Veiculo encontrado = null;

            for (Veiculo v : frota) {
                if (v.getId() == idBusca) {
                    encontrado = v;
                    break;
                }
            }

            if (encontrado != null) {
                veiculosSelecionados[quantidade] = encontrado;
                quantidade++;
            } else {
                System.out.println("Veiculo nao encontrado.");
            }
        }

        // Ordena por CO2 e transmissao
        Veiculo.heapSort(veiculosSelecionados, quantidade);

        // Imprime a saida formatada
        for (int i = 0; i < quantidade; i++) {
            System.out.println(veiculosSelecionados[i].format());
        }

        sc.close();
    }
}

/*
 * Uso de IA:
 * A ferramenta de Inteligencia Artificial foi utilizada como apoio
 * para fundamentacao, documentacao e compreensao do enunciado,
 * auxiliando na analise da logica e na revisao do codigo.
 *
 * A implementacao foi desenvolvida a partir do meu proprio
 * raciocinio e entendimento do problema, com testes e validacao
 * realizados por mim.
 */