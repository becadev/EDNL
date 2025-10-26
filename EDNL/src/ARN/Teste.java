package ARN;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List chaves_nos = new ArrayList();
        int opcao;
        int chave;

        do {
            System.out.println("---- MENU: ----");
            System.out.println("1 - Inserir Nó;");
            System.out.println("2 - Remover Nó; ");
            System.out.println("3 - Buscar Nó; ");
            System.out.println("3 - Mostrar ");
            opcao = input.nextInt();

            if(opcao == 1) {
                System.out.println("Digite a chave(int) do Nó: ");
                chave = input.nextInt();
                chaves_nos.add(chave);

            }

            if(opcao == 2) {
                System.out.printf("Chaves da ARN: %d\n", chaves_nos);
                System.out.println("Digite a chave(int) do Nó: ");
                chave = input.nextInt();
                if (chaves_nos.contains(chave)) 
                    chaves_nos.remove(chave);
            }


        } while(opcao != 0);
        input.close();
    }
}
