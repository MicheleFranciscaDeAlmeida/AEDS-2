import java.util.Scanner;

public class Main {
    public static int contChar(String str) {
        int i = 0; 
        int cont = 0;
        char caracter;
        

        while(i < str.length()) {
            caracter = str.charAt(i);
        if (caracter >= 'A' && caracter <= 'Z') {
            cont++;
        }
            i++;
        }
        return cont;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        while(!str.equals("FIM")) {
            System.out.println(contChar(str));
            str = sc.nextLine();
        }
        sc.close();
    }
}

/*
Uso de IA: ChatGPT utilizado como ferramenta de apoio ao estudo,
esclarecimento de dúvidas, pesquisa de documentação e depuração.
A implementação e as decisões sobre o código foram realizadas pela aluna.
*/