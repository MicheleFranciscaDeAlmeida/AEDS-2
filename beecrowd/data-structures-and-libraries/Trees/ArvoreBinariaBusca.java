import java.util.Scanner;

class No {
	int elemento;
	No esq;
	No dir;

	public No(int elemento) {
		this.elemento = elemento;
		this.esq = null;
		this.dir = null;
	}
}

class ArvoreBinaria {
	private No raiz;
	public ArvoreBinaria() {
		this.raiz = null;
	}

	// Inserir elemento na arvore
	public void inserir(int valor) {
		raiz = inserirRecursivo(raiz, valor);
	}

	private No inserirRecursivo(No atual, int valor) {
		if (atual == null) {
			return new No(valor);
		}

		if (valor < atual.elemento) {
			atual.esq = inserirRecursivo(atual.esq, valor);
		} else if (valor > atual.elemento) {
			atual.dir = inserirRecursivo(atual.dir, valor);
		}

		return atual;

	}

	// Caminhamento Pre-Ordem (raiz, esq,dir)

	public String caminharPre() {
		StringBuilder sb = new StringBuilder();
		caminharPreRecursivo(raiz, sb);
		return sb.toString().trim();
	}

	private void caminharPreRecursivo(No no, StringBuilder sb) {
		if (no != null) {
			sb.append(no.elemento).append(" ");
			caminharPreRecursivo(no.esq, sb);
			caminharPreRecursivo(no.dir, sb);
		}

	}

	// Caminhamento In-Ordem (esq, raiz, dir)
	public String caminharIn() {
		StringBuilder sb = new StringBuilder();
		caminharInRecursivo(raiz, sb);
		return sb.toString().trim();
	}

	private void caminharInRecursivo(No no, StringBuilder sb) {
		if (no != null) {
			caminharInRecursivo(no.esq, sb);
			sb.append(no.elemento).append(" ");
			caminharInRecursivo(no.dir, sb);
		}

	}

	// Caminhamento Pos-Ordem (esq,dir,raiz)
	public String caminharPos() {
		StringBuilder sb = new StringBuilder();
		caminharPosRecursivo(raiz, sb);
		return sb.toString().trim();
	}

	private void caminharPosRecursivo(No no, StringBuilder sb) {
		if (no != null) {
			caminharPosRecursivo(no.esq, sb);
			caminharPosRecursivo(no.dir, sb);
			sb.append(no.elemento).append(" ");
		}

	}

	// Calcular altura da arvore
	public int altura() {
		return alturaRecursivo(raiz);
	}

	private int alturaRecursivo(No no) {
		if (no == null) {
			// Arvore vazia -> altura - 1
			return - 1;
		}

		int alturaEsq = alturaRecursivo(no.esq);
		int alturaDir = alturaRecursivo(no.dir);

		return Math.max(alturaEsq, alturaDir) + 1; 
	}

}

public class ArvoreBinariaBusca {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Numero de casos de teste
		int C = scanner.nextInt();

		for (int caso = 1; caso <= C; caso++) {
			// Quantidade de numeros
			int N = scanner.nextInt();

			ArvoreBinaria arvore = new ArvoreBinaria();

			// Inserir os N elementos na arvore
			for (int i = 0; i < N; i++) {
				int valor = scanner.nextInt();
				arvore.inserir(valor);
			}

			// Impressao dos resultados
			System.out.println("Case " + caso + ":");
			System.out.println("Pre.: " + arvore.caminharPre());
			System.out.println("In..: " + arvore.caminharIn());
			System.out.println("Post: " + arvore.caminharPos());
			System.out.println();

		}

		scanner.close();

	}	
}	    
