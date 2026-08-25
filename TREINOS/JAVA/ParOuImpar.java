import java.util.Scanner;

public class ParOuImpar {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite um numero inteiro: ");
		int n1 = scanner.nextInt();

		if(n1 % 2 == 0) {
			System.out.println("Numero eh Par: ");
		} else {
			System.out.println("Numero eh Impar: ");

		scanner.close();
	  }
     }
 
}

