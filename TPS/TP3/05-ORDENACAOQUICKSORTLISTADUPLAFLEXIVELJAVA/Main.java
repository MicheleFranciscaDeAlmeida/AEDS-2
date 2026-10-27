import java.util.Scanner;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    // Busca um veiculo na frota pelo seu ID
    public static Veiculo buscarVeiculo(Veiculo[] frota, int id) {
        for (Veiculo v : frota) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        // Carrega os veiculos do arquivo CSV
        Veiculo[] frota = LeitorCsv.ler("/tmp/veiculos.csv");

        Scanner sc = new Scanner(System.in);
        ListaDupla lista = new ListaDupla();

        // Le os IDs ate encontrar -1
        while (sc.hasNextInt()) {
            int id = sc.nextInt();

            if (id == -1) {
                break;
            }

            // Procura o veiculo e insere no final da lista
            Veiculo encontrado = buscarVeiculo(frota, id);

            if (encontrado != null) {
                lista.inserirFim(encontrado);
            }
        }

        // Ordena a lista utilizando Quicksort
        lista.quicksort();

        // Exibe os veiculos ordenados
        lista.mostrar();

        sc.close();
    }
}

// Lista duplamente encadeada
class ListaDupla {

    private Celula primeiro;
    private Celula ultimo;

    // Celula que armazena o veiculo e as referencias aos vizinhos
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

    // Insere um veiculo no inicio da lista
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

    // Insere um veiculo no final da lista
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

    // Compara consumo na estrada e, em caso de empate, a marca
    private int comparar(Veiculo a, Veiculo b) {

        int resultado = Double.compare(
            a.getConsumoEstrada(),
            b.getConsumoEstrada()
        );

        if (resultado == 0) {
            resultado = a.getMarca().compareToIgnoreCase(b.getMarca());
        }

        return resultado;
    }

    // Inicia a ordenacao da lista
    public void quicksort() {
        int tamanho = 0;
        Celula atual = primeiro;

        while (atual != null) {
            tamanho++;
            atual = atual.prox;
        }

        if (tamanho > 1) {
            quicksort(primeiro, ultimo, tamanho);
        }
    }

    // Ordena recursivamente um trecho da lista
    private void quicksort(Celula esq, Celula dir, int tamanho) {

        if (tamanho <= 1 || esq == null || dir == null) {
            return;
        }

        // Localiza o elemento central para utilizar como pivo
        Celula meio = esq;

        for (int k = 0; k < (tamanho - 1) / 2; k++) {
            meio = meio.prox;
        }

        Veiculo pivo = meio.elemento;

        // Inicializa os ponteiros nas extremidades
        Celula i = esq;
        Celula j = dir;

        int posI = 0;
        int posJ = tamanho - 1;

        // Percorre o trecho pelos dois lados
        while (posI <= posJ) {

            // Avanca enquanto o elemento for menor que o pivo
            while (posI <= posJ && comparar(i.elemento, pivo) < 0) {
                i = i.prox;
                posI++;
            }

            // Recua enquanto o elemento for maior que o pivo
            while (posI <= posJ && comparar(j.elemento, pivo) > 0) {
                j = j.ant;
                posJ--;
            }

            // Troca os elementos enquanto os ponteiros nao se cruzarem
            if (posI <= posJ) {
                Veiculo temp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = temp;

                i = i.prox;
                posI++;

                j = j.ant;
                posJ--;
            }
        }

        // Ordena recursivamente a parte esquerda
        if (posJ > 0) {
            quicksort(esq, j, posJ + 1);
        }

        // Ordena recursivamente a parte direita
        if (posI < tamanho - 1) {
            quicksort(i, dir, tamanho - posI);
        }
    }

    // Percorre a lista e imprime os veiculos ordenados
    public void mostrar() {
        Celula atual = primeiro;

        while (atual != null) {
            System.out.println(atual.elemento.format());
            atual = atual.prox;
        }
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
}

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

    // Converte uma data no formato AAAA-MM-DD
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

    // Le o arquivo e retorna o array de veiculos
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
