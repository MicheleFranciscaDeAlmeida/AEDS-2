public class PesquisaBinaria {
	public static void main(String[] args) {

		int[] vetor = {10, 20, 30, 40, 50}; 
		int procurado = 30;
		int inicio = 0;
		int fim = vetor.length - 1;
		int meio;
		boolean encontrado = false;

	     while (inicio <= fim) {
		     meio = (inicio + fim) /2;

		     System.out.println("Meio: " + meio);

             if (procurado == vetor[meio]) {
		    encontrado = true;
		    break;
	     } else if (procurado < vetor[meio]) {
		     fim = meio - 1;
             } else {
	    		inicio = meio + 1;
	     } 
	  }
	  if (encontrado) {
	  	System.out.println("Elemento encontrado.");
	  } else {
	  System.out.println("Elemento nao encontrado.");
	}
     }
   }

