import java.util.Scanner;

public class Variavel {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite seu nome: ");
		String nome = scanner.nextLine();

		System.out.print("Digite sua idade: ");
		int idade = scanner.nextInt();

		System.out.print("Digite sua altura: ");
		double altura = scanner.nextDouble();

		System.out.print("É estudante? (true/false): ");
		boolean estudante = scanner.nextBoolean();

		scanner.close();
	}

   }

