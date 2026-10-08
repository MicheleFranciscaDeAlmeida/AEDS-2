public class PesquisaSequencial {
	public static void main(String[] args) {

		int[] vetor = {10, 20, 30, 40, 50}; 
		int procurado = 60;
		int contador = 0;
	       boolean encontrado = false;	

		for(int i = 0; i < vetor.length; i++) {
			contador++; 
			if(vetor[i] == procurado) {
				encontrado = true;
				System.out.println("Encontrado no indice " + i);
				break;
		}
	     }

	     if (!encontrado) {
		     System.out.println("Elemento nao encontrado");
             }

	     System.out.println("Comparacoes: " + contador);
	  }
     }

