import java.util.Scanner;

public class FiladoRecreio() {

// Insertion sort em ordem decrescente
public static void insertionSort(int[] arr) {
	
	int n = arr.length;
	
	for(int i = 1; i < n; i++) {
	int key = arr[i];
	int j = i - 1;

	// Desloca elementos menores que key para direita
	while (j >= 0 && arr[j] < key) {
		arr[j + 1] = arr[j];
		j--;
	}
	arr[j + 1] = key;
	}
	}


	public static void main(String[]args){
	Scanner scanner = new Scanner(System.in);
	
	// Número de casos de teste
	int n = scanner.nextInt();

	while (n-- > 0) {
	
	// Número de alunos
	int m = scanner.nextInt();
	
	int[] original = new int[m];
	int[] ordenado = new int[m];

	// Lê a ordem de chegada e faz a cópia
	for (inti =0; i < m; i++) {
		original[i] = scanner.nextInt();
		ordenado[i] = original[i];
	}
	
	// Reordena pela nota (decrescente)
	insertionSort(ordenado);
	

	// Conta quantos alunos não precisaram
	// trocar de lugar
	int count = 0; 
	for(int i = 0; i < m; i++) {
	   if(original[i] == ordenado[i]) {
		count++;
	}
     }
	System.out.println(count);
   }
	scanner.close;
  }
 }
