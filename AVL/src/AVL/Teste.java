package AVL;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Arvore avl = new Arvore();

        System.out.println("1.Teste");
        avl = new Arvore();
        avl.inserir(6);
        avl.inserir(8);
        avl.inserir(9);
        avl.imprimir();
        System.out.println();

        System.out.println("2.Teste");
        avl = new Arvore();
        avl.inserir(6);
        avl.inserir(5);
        avl.inserir(4);
        avl.imprimir();
        System.out.println();

        System.out.println("3.Teste");
        avl = new Arvore();
        avl.inserir(7);
        avl.inserir(5);
        avl.inserir(6);
        avl.imprimir();
        System.out.println();

        System.out.println("4.Teste");
        avl = new Arvore();
        avl.inserir(6);
        avl.inserir(8);
        avl.inserir(7);
        avl.imprimir();
        System.out.println();

        System.out.println("Teste 5");
        avl = new Arvore();
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int[] chaves_inseridas = {40,25,50,10,35,30,20,70};
        for(int chave:chaves_inseridas){
            System.out.printf("inserindo a chave %d%n: ", chave);
            avl.inserir(chave);
            avl.imprimir();
            System.out.println();
        }

        avl = new Arvore();
        System.out.println("Teste 6");
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int [] chaves_inseridas2 = {40,25,50,10,35,30,20,70};
        for(int chave:chaves_inseridas2){
            System.out.printf("inserindo chave %d%n", chave);
            avl.inserir(chave);
            avl.imprimir();
        }
        // System.out.println("Remoção chave 40");
        // avl.remocao(40);
        // avl.imprimir();
        // System.out.println();

        // System.out.println("Remoção chave 60");
        // avl.remocao(60);
        // avl.imprimir();
        // System.out.println();



        // do{
        //     System.out.println("---- TESTE INSERÇÃO ROTAÇÕES SIMPLES: ----");
        //     System.out.println("1 - Teste rotação simples esquerda com inserção");
        //     System.out.println("2 - Teste rotação simples direita com inserção");
        //     System.out.println();

        //     System.out.println("---- TESTE INSERÇÃO ROTAÇOES DUPLAS: ----");
        //     System.out.println("3 - Teste rotação dupla esquerda com inserção");
        //     System.out.println("4 - Teste rotação dupla direita com inserção");
        //     System.out.println();

        //     System.out.println("---- TESTE REMOÇÕES RSE TODOS OS CASOS: ----");
        //     System.out.println("5 - Teste rotação simples esquerda com remocao caso 1");
        //     System.out.println("6 - Teste rotação simples esquerda com remocao caso 2");
        //     System.out.println("7 - Teste rotação simples esquerda com remocao caso 3");
        //     System.out.println();

        //     System.out.println("---- TESTE REMOÇÕES RSD TODOS OS CASOS: ----");
        //     System.out.println("8 - Teste rotação simples direita com remocao caso 1");
        //     System.out.println("9 - Teste rotação simples direita com remocao caso 2");
        //     System.out.println("10 - Teste rotação simples direita com remocao caso 3");
        //     System.out.println();

        //     System.out.println("---- TESTE REMOÇÕES RDE TODOS OS CASOS: ----");
        //     System.out.println("11 - Teste rotação dupla esquerda com remocao caso 1");
        //     System.out.println("12 - Teste rotação dupla esquerda com remocao caso 2");
        //     System.out.println("13 - Teste rotação dupla esquerda com remocao caso 3");
        //     System.out.println();

        //     System.out.println("---- TESTE REMOÇÕES RDD TODOS OS CASOS: ----");
        //     System.out.println("14 - Teste rotação dupla direita com remocao caso 1");
        //     System.out.println("15 - Teste rotação dupla direita com remocao caso 2");
        //     System.out.println("16 - Teste rotação dupla direita com remocao caso 3");
        //     System.out.println("17 - Teste remoção Ex. Slides");
        //     System.out.println();

        //     System.out.print("Opção: ");
        //     opcao = input.nextInt();

            // if(opcao == 1) {// Rotação simples esquerda com inserção
            //     avl = new Arvore();
            //     avl.inserir(6);
            //     avl.inserir(8);
            //     avl.imprimir();
            //     System.out.println();

            //     avl.inserir(9);
            //     System.out.println();
            //     avl.imprimir();
            // }
            // if(opcao == 2) { // Rotação simples direita com inserção
            //     avl = new Arvore();
            //     // avl.inserir(6);
            //     // avl.inserir(5);
            //     // avl.imprimir();
            //     // avl.inserir(2);
            //     // System.out.println();
            //     // avl.imprimir();
            //     avl.inserir(50);
            //     avl.inserir(70);
            //     avl.inserir(60);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.imprimir();
            // }

            // if(opcao == 3) { // Teste rotação dupla esquerda com inserção
            //     // avl = new Arvore();
            //     // avl.inserir(50);
            //     // avl.imprimir();
            //     // System.out.println();
            //     // avl.inserir(20);
            //     // avl.imprimir();
            //     // System.out.println();
            //     // avl.inserir(80);
            //     // avl.imprimir();
            //     // System.out.println();
            //     // avl.inserir(70);
            //     // avl.imprimir();
            //     // System.out.println();
            //     // avl.inserir(90);
            //     // avl.imprimir();
            //     // System.out.println();
            //     // avl.inserir(60);
            //     // avl.imprimir();
            //     // System.out.println();
            //     avl = new Arvore();
            //     System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
            //     int[] chaves_inseridas = {40,25,50,10,35,30,20,70};
            //     for(int chave:chaves_inseridas){
            //         System.out.printf("inserindo a chave %d%n: ", chave);
            //         avl.inserir(chave);
            //         avl.imprimir();
            //         System.out.println();
            //     }
            // }

            // if(opcao == 4) { // Teste rotação dupla direita com inserção
            //     avl = new Arvore();
            //     avl.inserir(50);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(20);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(90);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(10);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(40);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(30);
            //     avl.imprimir();
            // }

            // if(opcao == 5) { // Rotação simples esquerda com remoção caso 1
            //     avl = new Arvore();
            //     avl.inserir(30);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(20);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(40);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(10);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(50);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(25);
            //     avl.imprimir();
            //     System.out.println();
            //     System.out.println("removendo chave 10");
            //     avl.remocao(10);
            //     avl.imprimir();
            //     System.out.println();
            // }
            // if(opcao == 6) { // Rotação simples esquerda com remoção caso 2
            //     avl = new Arvore();
            //     avl.inserir(30);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(20);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(40);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(10);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(50);
            //     avl.imprimir();
            //     System.out.println();
            //     System.out.println("removendo chave 20");
            //     avl.remocao(20);
            //     avl.imprimir();
            //     System.out.println();
            // }
            // if(opcao == 7) { // Rotação simples esquerda com remoção caso 3
            //     avl = new Arvore();
            //     avl.inserir(30);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(20);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(40);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(10);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(50);
            //     avl.imprimir();
            //     System.out.println();
            //     avl.inserir(25);
            //     avl.imprimir();
            //     System.out.println();
            //     System.out.println("removendo chave 20");
            //     avl.remocao(20);
            //     avl.imprimir();
            //     System.out.println();
            // }
            // if(opcao == 8){
            //     avl = new Arvore();
            //     System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
            //     int[] chaves_inseridas = {40,25,50,10,35,30,20,70,60};
            //     for(int chave:chaves_inseridas){
            //         avl.inserir(chave);
            //     }
            //     avl.remocao(40);
            //     avl.imprimir();
            //     System.out.println();
            // }

            // if(opcao == 17) {
            //     avl = new Arvore();
            //     System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
            //     int[] chaves_inseridas = {40,25,50,10,35,30,20,70,60};
            //     for(int chave:chaves_inseridas){
            //         System.out.printf("inserindo a chave %d%n: ", chave);
            //         avl.inserir(chave);
            //         avl.imprimir();
            //         System.out.println();
            //     }
            //     for(int chave : chaves_inseridas) {
            //         System.out.printf("Remoção da chave %d%n: ", chave);
            //         avl.imprimir();
            //         avl.remocao(chave);
            //         avl.imprimir();
            //         System.out.println();
            //     }
            // }

        // }while(opcao!=0);
        input.close();

    }
}
