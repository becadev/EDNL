package AVL;

import java.util.Scanner;

public class Teste {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Arvore avl = new Arvore();
        int opcao;
        do{
            System.out.println("1 - Teste rotação simples esquerda com inserção");
            System.out.println("2 - Teste rotação simples direita com inserção");

            // Remoção simples todos casos esq
            System.out.println("3 - Teste rotação simples esquerda com remocao caso 1");
            System.out.println("4 - Teste rotação simples esquerda com remocao caso 2");
            System.out.println("5 - Teste rotação simples esquerda com remocao caso 3");

            // Remoção simples todos casos dir
            System.out.println("6 - Teste rotação simples direita com remocao caso 1");
            System.out.println("7 - Teste rotação simples direita com remocao caso 2");
            System.out.println("8 - Teste rotação simples direita com remocao caso 3");

            // Rotação dupla inserções ambos esq e dir
            System.out.println("9 - Teste rotação dupla esquerda com inserção");
            System.out.println("10 - Teste rotação dupla direita com inserção");

            // Remoção dupla todos casos esq
            System.out.println("11 - Teste rotação dupla esquerda com remocao caso 1");
            System.out.println("12 - Teste rotação dupla esquerda com remocao caso 2");
            System.out.println("13 - Teste rotação dupla esquerda com remocao caso 3");

            // Remoção dupla todos casos dir
            System.out.println("14 - Teste rotação dupla direita com remocao caso 1");
            System.out.println("15 - Teste rotação dupla direita com remocao caso 2");
            System.out.println("16 - Teste rotação dupla direita com remocao caso 3");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            if(opcao == 1) {// Rotação simples esquerda com inserção
                avl = new Arvore();
                avl.inserir(6);
                avl.inserir(8);
                avl.imprimir();
                System.out.println();

                avl.inserir(9);
                System.out.println();
                avl.imprimir();
            }
            if(opcao == 2) { // Rotação simples direita com inserção
                avl = new Arvore();
                avl.inserir(6);
                avl.inserir(5);
                avl.imprimir();
                avl.inserir(2);
                System.out.println();
                avl.imprimir();
            }
            if(opcao == 3) { // Rotação simples esquerda com remoção caso 1
                avl = new Arvore();
                avl.inserir(30);
                avl.imprimir();
                System.out.println();
                avl.inserir(20);
                avl.imprimir();
                System.out.println();
                avl.inserir(40);
                avl.imprimir();
                System.out.println();
                avl.inserir(10);
                avl.imprimir();
                System.out.println();
                avl.inserir(50);
                avl.imprimir();
                System.out.println();
                avl.inserir(25);
                avl.imprimir();
                System.out.println();
                avl.remocao(10);
                avl.imprimir();
                System.out.println();
            }

            if(opcao == 9) {
                avl = new Arvore();
                avl.inserir(50);
                avl.imprimir();
                System.out.println();
                avl.inserir(20);
                avl.imprimir();
                System.out.println();
                avl.inserir(80);
                avl.imprimir();
                System.out.println();
                avl.inserir(70);
                avl.imprimir();
                System.out.println();
                avl.inserir(90);
                avl.imprimir();
                System.out.println();
                avl.inserir(60);
                avl.imprimir();
                System.out.println();
            }

            if(opcao == 10) {
                avl = new Arvore();
                avl.inserir(50);
                avl.imprimir();
                System.out.println();
                avl.inserir(20);
                avl.imprimir();
                System.out.println();
                avl.inserir(90);
                avl.imprimir();
                System.out.println();
                avl.inserir(10);
                avl.imprimir();
                System.out.println();
                avl.inserir(40);
                avl.imprimir();
                System.out.println();
                avl.inserir(30);
                avl.imprimir();
            }
        }while(opcao!=0);
        input.close();


    }
}
