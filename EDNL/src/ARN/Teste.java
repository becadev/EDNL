package ARN;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ARN arn = new ARN();
        // List chaves_nos = new ArrayList();
        // int opcao;
        // int chave;
        // System.out.println("inseridno 1");
        // arn.inserir(1);
        // arn.mostrar();
        // System.out.println();
        // System.out.println("inseridno 2");
        // arn.inserir(2);
        // arn.mostrar();
        // System.out.println();
        // System.out.println("inseridno 3");
        // arn.inserir(3);
        // arn.mostrar();
        // System.out.println();
        // System.out.println("inseridno 4");
        // arn.inserir(4);
        // arn.mostrar();
        // System.out.println();
        for(int i = 1 ; i <= 10 ; i++) {
            System.out.println("inserindo a chave: " + i);
            arn.inserir(i);
            arn.mostrar();
            System.out.println();
        }
        // arn.remocao(6);
        // arn.mostrar();
        // System.out.println();
        
        


        // do {
        //     System.out.println("---- MENU: ----");
        //     System.out.println("1 - Inserir Nó;");
        //     System.out.println("2 - Remover Nó; ");
        //     System.out.println("3 - Buscar Nó; ");
        //     System.out.println("3 - Mostrar ");
        //     opcao = input.nextInt();

        //     if(opcao == 1) {
        //         System.out.println("Digite a chave(int) do Nó: ");
        //         chave = input.nextInt();
        //         chaves_nos.add(chave);
        //         arn.inserir(chave);
        //     }

        //     if(opcao == 2) {
        //         System.out.printf("Chaves da ARN: %d\n", chaves_nos);
        //         System.out.println("Digite a chave(int) do Nó: ");
        //         chave = input.nextInt();
        //         if (chaves_nos.contains(chave)) 
        //             chaves_nos.remove(chave);
        //     }

        //     if (opcao == 3)
        //         arn.mostrar();


        // } while(opcao != 0);
        input.close();
    }
}
