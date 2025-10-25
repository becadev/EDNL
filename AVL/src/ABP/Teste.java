package ABP;

public class Teste {
    public static void main(String[] args) {
        ArvoreABP ArvoreABP = new ArvoreABP();

        System.out.println("1.Teste");
        ArvoreABP = new ArvoreABP();
        ArvoreABP.inserir(6);
        ArvoreABP.mostrar();
        ArvoreABP.inserir(8);
        ArvoreABP.inserir(9);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("2.Teste");
        ArvoreABP = new ArvoreABP();
        ArvoreABP.inserir(6);
        ArvoreABP.inserir(5);
        ArvoreABP.inserir(4);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("3.Teste");
        ArvoreABP = new ArvoreABP();
        ArvoreABP.inserir(7);
        ArvoreABP.inserir(5);
        ArvoreABP.inserir(6);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("4.Teste");
        ArvoreABP = new ArvoreABP();
        ArvoreABP.inserir(6);
        ArvoreABP.inserir(8);
        ArvoreABP.inserir(7);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("Teste 5");
        ArvoreABP = new ArvoreABP();
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int[] chaves_inseridas = {40,25,50,10,35,30,20,70};
        for(int chave:chaves_inseridas){
            System.out.printf("inserindo a chave %d%n: ", chave);
            ArvoreABP.inserir(chave);
            ArvoreABP.mostrar();
            System.out.println();
        }

        ArvoreABP = new ArvoreABP();
        System.out.println("Teste 6");
        System.out.println("Inserções das chaves 40,25,50,10,35,30,20,70 e 60");
        int [] chaves_inseridas2 = {40,25,50,10,35,30,20,70,60};
        for(int chave:chaves_inseridas2){
            System.out.printf("inserindo chave %d%n", chave);
            ArvoreABP.inserir(chave);
            ArvoreABP.mostrar();
        }
        System.out.println("Remoção chave 40");
        ArvoreABP.remocao(40);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("Remoção chave 60");
        ArvoreABP.remocao(60);
        ArvoreABP.mostrar();
        System.out.println();

        System.out.println("Remoção chave 70");
        ArvoreABP.remocao(70);
        ArvoreABP.mostrar();
        System.out.println();
    }
}
