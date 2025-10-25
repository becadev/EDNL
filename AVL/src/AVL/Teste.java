package AVL;

public class Teste {
    public static void main(String[] args) {
        AVL avl = new AVL();

        System.out.println("1.Teste");
        avl = new AVL();
        avl.inserir(6);
        avl.imprimir();
        avl.inserir(8);
        avl.inserir(9);
        avl.imprimir();
        System.out.println();

        System.out.println("2.Teste");
        avl = new AVL();
        avl.inserir(6);
        avl.inserir(5);
        avl.inserir(4);
        avl.imprimir();
        System.out.println();

        System.out.println("3.Teste");
        avl = new AVL();
        avl.inserir(7);
        avl.inserir(5);
        avl.inserir(6);
        avl.imprimir();
        System.out.println();

        System.out.println("4.Teste");
        avl = new AVL();
        avl.inserir(6);
        avl.inserir(8);
        avl.inserir(7);
        avl.imprimir();
        System.out.println();

        System.out.println("Teste 5");
        avl = new AVL();
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int[] chaves_inseridas = {40,25,50,10,35,30,20,70};
        for(int chave:chaves_inseridas){
            System.out.printf("inserindo a chave %d%n: ", chave);
            avl.inserir(chave);
            avl.imprimir();
            System.out.println();
        }

        avl = new AVL();
        System.out.println("Teste 6");
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int [] chaves_inseridas2 = {40,25,50,10,35,30,20,70,60};
        for(int chave:chaves_inseridas2){
            System.out.printf("inserindo chave %d%n", chave);
            avl.inserir(chave);
            avl.imprimir();
        }
        System.out.println("Remoção chave 40");
        avl.remocao(40);
        avl.imprimir();
        System.out.println();

        System.out.println("Remoção chave 60");
        avl.remocao(60);
        avl.imprimir();
        System.out.println();

        System.out.println("Remoção chave 70");
        avl.remocao(70);
        avl.imprimir();
        System.out.println();
    }
}
