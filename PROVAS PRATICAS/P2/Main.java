/*uma plataforma de programacao competitiva utiliza arvore binaria de pesquisa abb pra cadastrar competidores, identificados por um
codigo numerico e um nome. Implemente em java uma abb que utilize o codigo como chave de insercao e permita:
1. determinar a quantidade de ancestrais de um competidor consultado.
2. imprimir os nomes dos ancestrais, do pai ate a raiz.

utilize classes proprias pra representar os competidores e os nos da arvore, sem estruturas de dados prontos. os registros devem ser
inseridos na ordem de entrada, sem balanceamento.

entrada
a entrada contem varios casos de teste, ate eof. cada caso possui:
um inteiro N, indicando a quantidade de competidores.
N linhas com codigo nome;
uma linha com codigo do competidor consultado.

os codigos sao inteiros e unicos em cada caso. os nomes possuem ate 100 caracteres e podem conter espacos, o codigo consultado pode nao
existir na arvore.
saida
para cada caso, imprima duas linhas:
1 a quantidade de ancestrais, ou -1 se o codigo nao existir.
2 os nomes dos ancestrais, do pai ate a raiz, separados por | . Imprima NENHUM se nao houver ancestrais ou se o codigo nao existir.

Nao imprima linhas em branco entre os casos.

entrada 
7
50 Ana Paula
30 Bruno Reis
70 Carla Dias
20 Diego Alves
40 Elisa Rocha
60 Fabio Lima
80 Gabriela Souza
40

saida
2
Bruno Reis | Ana Paula*/


import java.util.Scanner;

class Competidor {
    int codigo;
    String nome;

    public Competidor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}

class No {
    Competidor competidor;
    No esquerda;
    No direita;

    public No(Competidor competidor) {
        this.competidor = competidor;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int codigo, String nome) {
        Competidor competidor = new Competidor(codigo, nome);
        raiz = inserirRec(raiz, competidor);
    }

    private No inserirRec(No atual, Competidor competidor) {
        if (atual == null) {
            return new No(competidor);
        }

        if (competidor.codigo < atual.competidor.codigo) {
            atual.esquerda = inserirRec(atual.esquerda, competidor);
        } else if (competidor.codigo > atual.competidor.codigo) {
            atual.direita = inserirRec(atual.direita, competidor);
        }

        return atual;
    }

    public void consultarAncestrais(int codigo) {
        No atual = raiz;
        String ancestrais = "";
        int quantidade = 0;

        while (atual != null && atual.competidor.codigo != codigo) {
            if (quantidade > 0) {
                ancestrais += " | ";
            }

            ancestrais += atual.competidor.nome;
            quantidade++;

            if (codigo < atual.competidor.codigo) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }

        if (atual == null) {
            System.out.println(-1);
            System.out.println("NENHUM");
        } else {
            System.out.println(quantidade);

            if (quantidade == 0) {
                System.out.println("NENHUM");
            } else {
                System.out.println(ancestrais);
            }
        }
    }
}

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();

            ArvoreBinaria arvore = new ArvoreBinaria();

            for (int i = 0; i < n; i++) {
                int codigo = sc.nextInt();
                String nome = sc.nextLine().trim();

                arvore.inserir(codigo, nome);
            }

            int codigoConsulta = sc.nextInt();
            arvore.consultarAncestrais(codigoConsulta);
        }

        sc.close();
    }
}