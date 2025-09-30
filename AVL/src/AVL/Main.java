package AVL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Arvore avl = new Arvore();
        No[] nos = new No[50];
        int opcao;
        do {
            System.out.println("Menu AVL:");
            System.out.println("Selecione uma opção: \n");

            // Métodos genéricos
            System.out.println("0 - Finalizar");
            System.out.println("1 - isEmpty");
            System.out.println("2 - size");
            System.out.println("3 - height");

            // Métodos de acesso
            System.out.println("4 - root");
            System.out.println("5 - parent");

            // Métodos de consulta
            System.out.println("6 - isRoot");
            System.out.println("7 - isInternal");
            System.out.println("8 - isExternal");
            System.out.println("9 - depth");

            // Métodos de atualização
            System.out.println("10 - replace");

            // Métodos de busca
            System.out.println("11 - treeSearch");

            // Inserção / remoção
            System.out.println("12 - inserir");
            System.out.println("13 - remocao");
            System.out.println("14 - imprimir Árvore");
            System.out.print("Opção: ");
            opcao = input.nextInt();

            if (opcao == 0) {
                System.out.println("Finalizando...");
                break;
            }
            if (opcao == 1) {
                System.out.println("Método isEmpty");
                System.out.println(avl.isEmpty());
            }
            if (opcao == 2) {
                System.out.println("Método size");
                System.out.println(avl.size());
            }
            if (opcao == 3) {
                System.out.println("Método height");
                if (avl.root() != null)
                    System.out.println(avl.height(avl.root()));
                else
                    System.out.println("Árvore vazia!");
            }
            if (opcao == 4) {
                System.out.println("Método root");
                System.out.println(avl.root().chave);
            }
            if (opcao == 5) {
                System.out.println("Método parent");
                // aqui precisa de um nó específico
                System.out.println("Informe a chave do nó:");
                int chave = input.nextInt();
                No no = avl.treeSearch(chave, avl.root());
                nos[avl.size()] = no;
                if (no != null)
                    System.out.println(avl.parent(chave));
                else
                    System.out.println("Nó não está presente na Árvore");
            }
            if (opcao == 6) {
                System.out.println("Método isRoot");
                if (avl.root() != null)
                    System.out.println(avl.isRoot(avl.root()));
                else
                    System.out.println("Árvore vazia!");
            }
            if (opcao == 7) {
                System.out.println("Método isInternal");
                if (avl.root() != null)
                    System.out.println(avl.isInternal(avl.root()));
                else
                    System.out.println("Árvore vazia!");
            }
            if (opcao == 8) {
                System.out.println("Método isExternal");
                if (avl.root() != null)
                    System.out.println(avl.isExternal(avl.root()));
                else
                    System.out.println("Árvore vazia!");
            }
            if (opcao == 9) {
                System.out.println("Método depth");
                if (avl.root() != null)
                    System.out.println(avl.depth(avl.root()));
                else
                    System.out.println("Árvore vazia!");
            }
            if (opcao == 10) {
                System.out.println("Método replace");
                System.out.println("Informe a chave do nó:");
                int chave = input.nextInt();
                No no = avl.treeSearch(chave, avl.root());
                if (no != null) {
                    System.out.println("Informe o novo valor:");
                    int novo = input.nextInt();
                    avl.replace(no, novo);
                    System.out.println("Valor atualizado!");
                } else {
                    System.out.println("Nó não encontrado!");
                }
            }
            if (opcao == 11) {
                System.out.println("Método treeSearch");
                System.out.println("Informe a chave do nó:");
                int chave = input.nextInt();
                No no = avl.treeSearch(chave, avl.root());
                System.out.println(no != null ? "Nó encontrado: " + no.chave : "Nó não encontrado");
            }
            if (opcao == 12) {
                System.out.println("Método inserir");
                System.out.println("Informe a chave do novo nó:");
                int chave = input.nextInt();
                No novo = new No(chave);
                nos[avl.size()] = novo;
                avl.inserir(chave);
                System.out.println("Nó inserido!");
            }
            if (opcao == 13) {
                System.out.println("Método remocao");
                System.out.println("Informe a chave do nó a ser removido:");
                System.out.println("Chaves presentes na Árvore: ");
                for(No no : nos){
                    if(no != null)
                        System.out.print(no.chave + " ");
                }
                System.out.println();
                int chave = input.nextInt();
                avl.remocao(chave);
//                for (No no : nos) {
//                    if (no.chave == chave)
//                        no = null;
//                }
                System.out.println("Remoção concluída!");
            }
            if (opcao == 14) {
                System.out.println("Imprimir árvore ");
                avl.imprimir(avl.root());
                System.out.println();
            }


        } while (opcao != 0);
        input.close();
    }
}
